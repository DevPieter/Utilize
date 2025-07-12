package nl.devpieter.utilize.events.inventory;

import nl.devpieter.sees.event.SEvent;

public record HotbarSlotChangedEvent(int previous, int current) implements SEvent {
}
