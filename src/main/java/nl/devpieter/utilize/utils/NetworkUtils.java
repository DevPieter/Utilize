package nl.devpieter.utilize.utils;

import net.minecraft.network.packet.Packet;

public class NetworkUtils {

    public static void sendPacket(Packet<?> packet) {
        if (!ClientUtils.hasNetworkHandler()) return;
        ClientUtils.getNetworkHandler().sendPacket(packet);
    }
}
