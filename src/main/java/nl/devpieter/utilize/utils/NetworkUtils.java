package nl.devpieter.utilize.utils;

import net.minecraft.network.packet.Packet;

public class NetworkUtils {

    public static void sendPacket(Packet<?> packet) {
        if (!ClientUtils.hasNetworkHandler()) return;
        ClientUtils.getNetworkHandler().sendPacket(packet);
    }

    public static void sendChatMessage(String message) {
        if (!ClientUtils.hasNetworkHandler()) return;

        if (message.startsWith("/")) sendChatCommand(message);
        else ClientUtils.getNetworkHandler().sendChatMessage(message);
    }

    public static void sendChatCommand(String command) {
        if (!ClientUtils.hasNetworkHandler()) return;
        if (command.startsWith("/")) command = command.substring(1);

        ClientUtils.getNetworkHandler().sendChatCommand(command);
    }
}
