package nl.devpieter.utilize.events.packet;

import net.minecraft.village.TradeOfferList;
import nl.devpieter.sees.Event.CancelableEventBase;

public class TradesOfferedPacketEvent extends CancelableEventBase {

    private final int syncId;
    private final TradeOfferList tradeOffers;

    public TradesOfferedPacketEvent(int syncId, TradeOfferList tradeOffers) {
        this.syncId = syncId;
        this.tradeOffers = tradeOffers;
    }

    public int syncId() {
        return syncId;
    }

    public TradeOfferList tradeOffers() {
        return tradeOffers;
    }
}
