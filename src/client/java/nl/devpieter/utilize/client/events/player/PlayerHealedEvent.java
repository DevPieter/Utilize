package nl.devpieter.utilize.client.events.player;

import nl.devpieter.sees.event.SEvent;

public record PlayerHealedEvent(double current, double heal) implements SEvent {
}
