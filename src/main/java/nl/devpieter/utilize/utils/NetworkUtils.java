package nl.devpieter.utilize.utils;

import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;

public class NetworkUtils {

    public static void sendPacket(@NotNull Packet<?> packet) {
        if (!ClientUtils.hasNetworkHandler()) return;
        ClientUtils.getNetworkHandler().sendPacket(packet);
    }

    public static void sendChatMessage(@NotNull String message) {
        if (!ClientUtils.hasNetworkHandler()) return;

        if (message.startsWith("/")) sendChatCommand(message);
        else ClientUtils.getNetworkHandler().sendChatMessage(message);
    }

    public static void sendChatCommand(@NotNull String command) {
        if (!ClientUtils.hasNetworkHandler()) return;
        if (command.startsWith("/")) command = command.substring(1);

        ClientUtils.getNetworkHandler().sendChatCommand(command);
    }
}
