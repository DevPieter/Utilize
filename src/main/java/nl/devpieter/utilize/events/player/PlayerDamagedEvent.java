package nl.devpieter.utilize.events.player;

import nl.devpieter.sees.event.SEvent;

public record PlayerDamagedEvent(double current, double damage) implements SEvent {
}