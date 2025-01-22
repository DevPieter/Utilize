package nl.devpieter.utilize.events.interaction;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import nl.devpieter.sees.Event.CancelableEventBase;

public class InteractItemEvent extends CancelableEventBase {

    private final PlayerEntity player;
    private final Hand hand;

    public InteractItemEvent(PlayerEntity player, Hand hand) {
        this.player = player;
        this.hand = hand;
    }

    public PlayerEntity player() {
        return player;
    }

    public Hand hand() {
        return hand;
    }
}
