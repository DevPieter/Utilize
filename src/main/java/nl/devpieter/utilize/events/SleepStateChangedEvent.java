package nl.devpieter.utilize.events;

import nl.devpieter.sees.Event.Event;
import nl.devpieter.utilize.enums.SleepState;

public record SleepStateChangedEvent(SleepState previous, SleepState current) implements Event {
}
