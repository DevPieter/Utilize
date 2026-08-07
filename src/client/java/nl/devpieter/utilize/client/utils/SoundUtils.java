package nl.devpieter.utilize.client.utils;

import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

public final class SoundUtils {

    private SoundUtils() {
    }

    public static void playUi(@NotNull SoundEvent soundEvent) {
        playUi(soundEvent, 1.0F, 1.0F);
    }

    public static void playUi(@NotNull SoundEvent soundEvent, float pitch) {
        playUi(soundEvent, pitch, 1.0F);
    }

    public static void playUi(@NotNull SoundEvent soundEvent, float pitch, float volume) {
        play(SimpleSoundInstance.forUI(soundEvent, pitch, volume));
    }

    public static void play(@NotNull SoundInstance soundInstance) {
        if (!ClientUtils.hasSoundManager()) return;
        ClientUtils.getSoundManager().play(soundInstance);
    }
}
