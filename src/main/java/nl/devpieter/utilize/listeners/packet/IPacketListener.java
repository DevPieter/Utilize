package nl.devpieter.utilize.listeners.packet;

import net.minecraft.network.packet.Packet;

import java.lang.reflect.Type;

public interface IPacketListener<T extends Packet<?>> {

    Type getPacketType();

    boolean onPacket(T packet);
}
