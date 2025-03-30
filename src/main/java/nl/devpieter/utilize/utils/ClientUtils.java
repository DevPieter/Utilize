package nl.devpieter.utilize.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;

public class ClientUtils {

    @Deprecated(since = "1.0.3", forRemoval = true)
    public static void sendMessage(Text message, boolean overlay) {
        PlayerUtils.sendMessage(message, overlay);
    }

    @Deprecated(since = "1.0.3", forRemoval = true)
    public static void sendPacket(Packet<?> packet) {
        NetworkUtils.sendPacket(packet);
    }

    public static MinecraftClient getClient() {
        return MinecraftClient.getInstance();
    }

    public static boolean hasPlayer() {
        return getClient().player != null;
    }

    public static ClientPlayerEntity getPlayer() {
        return getClient().player;
    }

    public static boolean hasWorld() {
        return getClient().world != null;
    }

    public static ClientWorld getWorld() {
        return getClient().world;
    }

    public static boolean hasSoundManager() {
        return getClient().getSoundManager() != null;
    }

    public static SoundManager getSoundManager() {
        return getClient().getSoundManager();
    }

    public static boolean hasNetworkHandler() {
        return getClient().getNetworkHandler() != null;
    }

    public static ClientPlayNetworkHandler getNetworkHandler() {
        return getClient().getNetworkHandler();
    }

    public static boolean hasInteractionManager() {
        return getClient().interactionManager != null;
    }

    public static ClientPlayerInteractionManager getInteractionManager() {
        return getClient().interactionManager;
    }
}
