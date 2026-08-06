package nl.devpieter.utilize.client.mixin;

import net.minecraft.client.KeyMapping;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.client.enums.KeyActionOverride;
import nl.devpieter.utilize.client.events.interaction.keybinding.AttackKeyPressedEvent;
import nl.devpieter.utilize.client.events.interaction.keybinding.KeyBindingPressedCheckEvent;
import nl.devpieter.utilize.client.events.interaction.keybinding.KeyBindingPressedEvent;
import nl.devpieter.utilize.client.events.interaction.keybinding.UseKeyPressedEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyMapping.class)
public abstract class KeyMappingMixin {

    @Shadow
    public abstract String getName();

    @Unique
    private final Sees sees = Sees.getSharedInstance();

    @Inject(at = @At("RETURN"), method = "isDown", cancellable = true)
    private void isPressed(CallbackInfoReturnable<Boolean> cir) {
        handle(cir);
        if (cir.getReturnValue()) handlePress(cir);
    }

    @Inject(at = @At("RETURN"), method = "consumeClick", cancellable = true)
    private void wasPressed(CallbackInfoReturnable<Boolean> cir) {
        handle(cir);
        if (cir.getReturnValue()) handlePress(cir);
    }

    @Unique
    private void handle(@NotNull CallbackInfoReturnable<Boolean> cir) {
        KeyActionOverride override = sees.dispatchWithResult(new KeyBindingPressedCheckEvent(getName()));
        if (override == KeyActionOverride.FORCE_RELEASE) forceRelease(cir);
        else if (override == KeyActionOverride.FORCE_PRESS) forcePress(cir);
    }

    @Unique
    private void handlePress(@NotNull CallbackInfoReturnable<Boolean> cir) {
        String keyName = getName();

        if (keyName.equals("key.attack")) {
            if (sees.dispatch(new AttackKeyPressedEvent())) forceRelease(cir);
        } else if (keyName.equals("key.use")) {
            if (sees.dispatch(new UseKeyPressedEvent())) forceRelease(cir);
        }

        KeyActionOverride override = sees.dispatchWithResult(new KeyBindingPressedEvent(keyName));
        if (override == KeyActionOverride.FORCE_RELEASE) forceRelease(cir);
        else if (override == KeyActionOverride.FORCE_PRESS) forcePress(cir);
    }

    @Unique
    private void forceRelease(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Unique
    private void forcePress(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
