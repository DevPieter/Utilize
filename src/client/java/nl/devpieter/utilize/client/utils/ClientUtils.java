package nl.devpieter.utilize.client.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

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
}
