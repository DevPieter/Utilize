package nl.devpieter.utilize.listeners.packet;

import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.events.packet.ScreenOpenedPacketEvent;

import java.lang.reflect.Type;

public class OpenScreenPacketListener implements IPacketListener<OpenScreenS2CPacket> {

    private final Sees sees = Sees.getInstance();

    @Override
    public Type getPacketType() {
        return OpenScreenS2CPacket.class;
    }

    @Override
    public boolean onPacket(OpenScreenS2CPacket packet) {
        ScreenOpenedPacketEvent event = new ScreenOpenedPacketEvent(packet.getSyncId(), packet.getScreenHandlerType(), packet.getName());
        if (!this.sees.dispatch(event)) return false;

        Utilize.blockScreenId(packet.getSyncId());
        return false;
    }
}
