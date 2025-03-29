package nl.devpieter.utilize.events.inventory;

import nl.devpieter.sees.Event.Event;

public record TotemHoldingChangedEvent(
        boolean previousMainHand, boolean currentMainHand,
        boolean previousOffhand, boolean currentOffhand
) implements Event {
}
