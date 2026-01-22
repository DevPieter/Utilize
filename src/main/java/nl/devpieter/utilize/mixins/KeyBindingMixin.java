package nl.devpieter.utilize.mixins;

import net.minecraft.client.option.KeyBinding;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.enums.KeyActionOverride;
import nl.devpieter.utilize.events.interaction.keybinding.AttackKeyPressedEvent;
import nl.devpieter.utilize.events.interaction.keybinding.KeyBindingPressedCheckEvent;
import nl.devpieter.utilize.events.interaction.keybinding.KeyBindingPressedEvent;
import nl.devpieter.utilize.events.interaction.keybinding.UseKeyPressedEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin {

    @Shadow
    private int timesPressed;

    @Shadow
    public abstract void setPressed(boolean pressed);

    @Shadow
    public abstract String getId();

    @Unique
    private final Sees sees = Sees.getSharedInstance();

    @Inject(at = @At("RETURN"), method = "isPressed", cancellable = true)
    private void isPressed(CallbackInfoReturnable<Boolean> cir) {
        handle(cir);
        if (cir.getReturnValue()) handlePress(cir);
    }

    @Inject(at = @At("RETURN"), method = "wasPressed", cancellable = true)
    private void wasPressed(CallbackInfoReturnable<Boolean> cir) {
        handle(cir);
        if (cir.getReturnValue()) handlePress(cir);
    }

    @Unique
    private void handle(@NotNull CallbackInfoReturnable<Boolean> cir) {
        KeyActionOverride override = sees.dispatchWithResult(new KeyBindingPressedCheckEvent(getId()));
        if (override == KeyActionOverride.FORCE_RELEASE) forceRelease(cir);
        else if (override == KeyActionOverride.FORCE_PRESS) forcePress(cir);
    }

    @Unique
    private void handlePress(@NotNull CallbackInfoReturnable<Boolean> cir) {
        String keyId = getId();

        if (keyId.equals("key.attack")) {
            if (sees.dispatch(new AttackKeyPressedEvent())) forceRelease(cir);
        } else if (keyId.equals("key.use")) {
            if (sees.dispatch(new UseKeyPressedEvent())) forceRelease(cir);
        }

        KeyActionOverride override = sees.dispatchWithResult(new KeyBindingPressedEvent(keyId));
        if (override == KeyActionOverride.FORCE_RELEASE) forceRelease(cir);
        else if (override == KeyActionOverride.FORCE_PRESS) forcePress(cir);
    }

    @Unique
    private void forceRelease(CallbackInfoReturnable<Boolean> cir) {
        timesPressed = 0;
        setPressed(false);
        cir.setReturnValue(false);
    }

    @Unique
    private void forcePress(CallbackInfoReturnable<Boolean> cir) {
        if (timesPressed < 1) timesPressed = 1;
        setPressed(true);
        cir.setReturnValue(true);
    }
}
