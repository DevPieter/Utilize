package nl.devpieter.utilize.events.inventory;

import nl.devpieter.sees.Event.Event;

public record TotemCountChangedEvent(int previous, int current) implements Event {
}
