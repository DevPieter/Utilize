package nl.devpieter.utilize.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.screen.ScreenChangedEvent;
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
    private final Sees sees = Sees.getInstance();

    @Unique
    private Screen previousScreen;

    @Inject(at = @At("HEAD"), method = "setScreen")
    private void onSetScreenHead(Screen screen, CallbackInfo ci) {
        this.previousScreen = this.currentScreen;
    }

    @Inject(at = @At("TAIL"), method = "setScreen")
    private void onSetScreenTail(Screen screen, CallbackInfo ci) {
        this.sees.dispatch(new ScreenChangedEvent(this.previousScreen, screen));
    }
}
