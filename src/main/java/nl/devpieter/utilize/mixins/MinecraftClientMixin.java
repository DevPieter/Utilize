package nl.devpieter.utilize.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.events.screen.ScreenChangedEvent;
import nl.devpieter.utilize.events.tick.ClientTickEvent;
import nl.devpieter.utilize.events.tick.ClientTickTailEvent;
import nl.devpieter.utilize.setting.SettingManager;
import nl.devpieter.utilize.task.TaskManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow
    @Nullable
    public Screen currentScreen;

    @Unique
    private Screen previousScreen;

    @Unique
    private final Sees sees = Sees.getInstance();

    @Unique
    private SettingManager settingManager;

    @Unique
    private TaskManager taskManager;

    @Inject(at = @At("HEAD"), method = "setScreen")
    private void onSetScreenHead(Screen screen, CallbackInfo ci) {
        previousScreen = currentScreen;
    }

    @Inject(at = @At("TAIL"), method = "setScreen")
    private void onSetScreenTail(Screen screen, CallbackInfo ci) {
        sees.dispatch(new ScreenChangedEvent(previousScreen, screen));
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo ci) {
        tryInitializeManagers();

        if (taskManager != null) {
            taskManager.beginTick();
            taskManager.tick(TaskManager.TickPhase.CLIENT_HEAD);
        }

        sees.dispatch(new ClientTickEvent());
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTickTail(CallbackInfo ci) {
        if (settingManager != null) settingManager.tick();
        if (taskManager != null) taskManager.tick(TaskManager.TickPhase.CLIENT_TAIL);

        sees.dispatch(new ClientTickTailEvent());
    }

    @Unique
    private void tryInitializeManagers() {
        if (settingManager != null || taskManager != null) return;
        if (!Utilize.getInstance().isInitialized()) return;

        if (settingManager == null) {
            settingManager = SettingManager.getInstance();
        }

        if (taskManager == null) {
            taskManager = TaskManager.getInstance();
        }
    }
}
