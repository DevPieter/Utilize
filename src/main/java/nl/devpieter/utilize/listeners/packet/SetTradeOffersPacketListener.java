package nl.devpieter.utilize.listeners.packet;

import net.minecraft.network.packet.s2c.play.SetTradeOffersS2CPacket;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.events.packet.TradesOfferedPacketEvent;

import java.lang.reflect.Type;

public class SetTradeOffersPacketListener implements IPacketListener<SetTradeOffersS2CPacket> {

    private final Sees sees = Sees.getInstance();

    @Override
    public Type getPacketType() {
        return SetTradeOffersS2CPacket.class;
    }

    @Override
    public boolean onPacket(SetTradeOffersS2CPacket packet) {
        TradesOfferedPacketEvent event = new TradesOfferedPacketEvent(packet.getSyncId(), packet.getOffers());
        if (!this.sees.dispatch(event)) return false;

        Utilize.blockScreenId(packet.getSyncId());
        return true;
    }
}
