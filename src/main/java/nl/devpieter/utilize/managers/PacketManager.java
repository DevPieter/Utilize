package nl.devpieter.utilize.managers;

import net.minecraft.network.packet.Packet;
import nl.devpieter.utilize.listeners.packet.IPacketListener;

import java.util.ArrayList;
import java.util.List;

public final class PacketManager {

    private static final PacketManager INSTANCE = new PacketManager();

    private final List<IPacketListener<?>> listeners = new ArrayList<>();

    private PacketManager() {
    }

    public static PacketManager getInstance() {
        return INSTANCE;
    }

    public void subscribe(IPacketListener<?> listener) {
        listeners.add(listener);
    }

    public void unsubscribe(IPacketListener<?> listener) {
        listeners.remove(listener);
    }

    public boolean packetReceived(Packet<?> packet) {
        boolean cancel = false;

        for (IPacketListener<?> listener : listeners) {
            if (!listener.getPacketType().equals(packet.getClass())) continue;
            if (((IPacketListener<Packet<?>>) listener).onPacket(packet)) cancel = true;
        }

        return cancel;
    }
}
