package nl.devpieter.utilize.events.player;

import nl.devpieter.sees.Event.Event;

public record PlayerHealedEvent(double current, double heal) implements Event {
}
