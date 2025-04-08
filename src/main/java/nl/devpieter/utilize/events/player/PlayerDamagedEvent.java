package nl.devpieter.utilize.events.player;

import nl.devpieter.sees.Event.Event;

public record PlayerDamagedEvent(double current, double damage) implements Event {
}