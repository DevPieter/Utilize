package nl.devpieter.utilize.client.mixin;

import net.minecraft.client.Minecraft;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.client.events.tick.ClientTickEvent;
import nl.devpieter.utilize.client.events.tick.ClientTickTailEvent;
import nl.devpieter.utilize.client.setting.SettingManager;
import nl.devpieter.utilize.client.task.TaskManager;
import nl.devpieter.utilize.client.task.enums.TickPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Unique
    private final Sees sees = Sees.getSharedInstance();

    @Unique
    private SettingManager settingManager;

    @Unique
    private TaskManager taskManager;

    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo ci) {
        tryInitializeManagers();

        if (taskManager != null) {
            taskManager.beginTick();
            taskManager.tick(TickPhase.CLIENT_HEAD);
        }

        sees.dispatch(new ClientTickEvent());
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTickTail(CallbackInfo ci) {
        if (settingManager != null) settingManager.tick();
        if (taskManager != null) taskManager.tick(TickPhase.CLIENT_TAIL);

        sees.dispatch(new ClientTickTailEvent());
    }

    /**
     * We need to initialize the managers here because Utilize may not be fully initialized
     * when Minecraft is being constructed, leading to potential null references.
     */
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
