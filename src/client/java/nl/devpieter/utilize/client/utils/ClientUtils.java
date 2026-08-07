package nl.devpieter.utilize.client.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.SoundManager;

public final class ClientUtils {

    private ClientUtils() {
    }

    public static boolean isDevEnv() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static Minecraft getClient() {
        return Minecraft.getInstance();
    }

    public static boolean hasPlayer() {
        return getClient().player != null;
    }

    public static LocalPlayer getPlayer() {
        return getClient().player;
    }

    public static boolean hasLevel() {
        return getClient().level != null;
    }

    public static ClientLevel getLevel() {
        return getClient().level;
    }

    public static boolean hasConnection() {
        return getClient().getConnection() != null;
    }

    public static ClientPacketListener getConnection() {
        return getClient().getConnection();
    }

    public static boolean hasSoundManager() {
        return getClient().getSoundManager() != null;
    }

    public static SoundManager getSoundManager() {
        return getClient().getSoundManager();
    }
}
