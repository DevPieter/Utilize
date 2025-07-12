package nl.devpieter.utilize.events.interaction;

import net.minecraft.util.Hand;
import nl.devpieter.sees.event.SCancelableEventBase;

public class InteractItemEvent extends SCancelableEventBase {

    private final Hand hand;

    public InteractItemEvent(Hand hand) {
        this.hand = hand;
    }

    public Hand hand() {
        return hand;
    }
}
