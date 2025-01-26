package nl.devpieter.utilize.utils;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
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
        SoundManager soundManager = ClientUtils.getClient().getSoundManager();
        if (soundManager != null) soundManager.play(soundInstance);
    }
}
