package nl.devpieter.utilize.mixins;

import net.minecraft.client.option.KeyBinding;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.interaction.keybinding.AttackKeyPressedEvent;
import nl.devpieter.utilize.events.interaction.keybinding.UseKeyPressedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin {

    @Shadow
    public abstract String getTranslationKey();

    @Shadow
    private int timesPressed;

    @Shadow
    public abstract void setPressed(boolean pressed);

    @Unique
    private final Sees sees = Sees.getInstance();

    @Inject(at = @At("RETURN"), method = "isPressed", cancellable = true)
    private void isPressed(CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        if (this.getTranslationKey().equals("key.attack")) this.handleAttackKeyPressed(cir);
        else if (this.getTranslationKey().equals("key.use")) this.handleUseKeyPressed(cir);
    }

    @Inject(at = @At("RETURN"), method = "wasPressed", cancellable = true)
    private void wasPressed(CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        if (this.getTranslationKey().equals("key.attack")) this.handleAttackKeyPressed(cir);
        else if (this.getTranslationKey().equals("key.use")) this.handleUseKeyPressed(cir);
    }

    @Unique
    private void handleAttackKeyPressed(CallbackInfoReturnable<Boolean> cir) {
        if (this.sees.dispatch(new AttackKeyPressedEvent())) this.cancel(cir);
    }

    @Unique
    private void handleUseKeyPressed(CallbackInfoReturnable<Boolean> cir) {
        if (this.sees.dispatch(new UseKeyPressedEvent())) this.cancel(cir);
    }

    @Unique
    private void cancel(CallbackInfoReturnable<Boolean> cir) {
        this.timesPressed = 0;
        this.setPressed(false);
        cir.setReturnValue(false);
    }
}
