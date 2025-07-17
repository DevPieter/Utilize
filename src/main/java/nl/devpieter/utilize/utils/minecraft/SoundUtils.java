package nl.devpieter.utilize.utils.minecraft;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class SoundUtils {

    public static void playOnMaster(@NotNull SoundEvent soundEvent) {
        playOnMaster(soundEvent, 1.0F, 1.0F);
    }

    public static void playOnMaster(@NotNull SoundEvent soundEvent, float pitch) {
        playOnMaster(soundEvent, pitch, 1.0F);
    }

    public static void playOnMaster(@NotNull SoundEvent soundEvent, float pitch, float volume) {
        play(PositionedSoundInstance.master(soundEvent, pitch, volume));
    }

    public static void play(@NotNull SoundInstance soundInstance) {
        if (!ClientUtils.hasSoundManager()) return;
        ClientUtils.getSoundManager().play(soundInstance);
    }
}
