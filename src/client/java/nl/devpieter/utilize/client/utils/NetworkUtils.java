package nl.devpieter.utilize.client.utils;

import net.minecraft.network.protocol.Packet;
import org.jetbrains.annotations.NotNull;

public final class NetworkUtils {

    private NetworkUtils() {
    }

    public static void sendPacket(@NotNull Packet<?> packet) {
        if (!ClientUtils.hasConnection()) return;
        ClientUtils.getConnection().send(packet);
    }

    public static void sendChatMessage(@NotNull String message) {
        if (!ClientUtils.hasConnection()) return;

        if (message.startsWith("/")) sendChatCommand(message);
        else ClientUtils.getConnection().sendChat(message);
    }

    public static void sendChatCommand(@NotNull String command) {
        if (!ClientUtils.hasConnection()) return;
        if (command.startsWith("/")) command = command.substring(1);

        ClientUtils.getConnection().sendCommand(command);
    }
}
