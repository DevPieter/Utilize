package nl.devpieter.utilize.packet;

import net.minecraft.network.protocol.Packet;

import java.lang.reflect.Type;

public interface IPacketListener<T extends Packet<?>> {

    Type getPacketType();

    boolean onPacket(T packet);
}
