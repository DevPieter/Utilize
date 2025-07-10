package nl.devpieter.utilize.mixins;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.sound.PlaySoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundSystem.class)
public class SoundSystemMixin {

    @Unique
    private final Sees sees = Sees.getInstance();

    @Inject(at = @At("HEAD"), method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", cancellable = true)
    private void onPlay(SoundInstance sound, CallbackInfo ci) {
        if (!this.sees.call(new PlaySoundEvent(sound))) return;
        ci.cancel();
    }
}
