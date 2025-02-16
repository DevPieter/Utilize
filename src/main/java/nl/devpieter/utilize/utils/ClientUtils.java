package nl.devpieter.utilize.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ClientUtils {

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

    public static void sendMessage(Text message, boolean overlay) {
        if (hasPlayer()) getPlayer().sendMessage(message, overlay);
    }

    public static @Nullable ClientPlayerInteractionManager getInteractionManager() {
        return getClient().interactionManager;
    }

    public static @Nullable ClientPlayNetworkHandler getNetworkHandler() {
        return getClient().getNetworkHandler();
    }

    public static void sendPacket(Packet<?> packet) {
        if (getNetworkHandler() != null) getNetworkHandler().sendPacket(packet);
    }
}
