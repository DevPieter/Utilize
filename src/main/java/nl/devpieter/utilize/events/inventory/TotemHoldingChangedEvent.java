package nl.devpieter.utilize.events.inventory;

import nl.devpieter.sees.event.SEvent;

public record TotemHoldingChangedEvent(
        boolean previousMainHand, boolean currentMainHand,
        boolean previousOffhand, boolean currentOffhand
) implements SEvent {
}
