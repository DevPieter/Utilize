package nl.devpieter.utilize.events.player;

import nl.devpieter.sees.event.SEvent;

public record PlayerHealedEvent(double current, double heal) implements SEvent {
}
