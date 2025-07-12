package nl.devpieter.utilize.events.player;

import nl.devpieter.sees.event.SEvent;
import org.jetbrains.annotations.NotNull;

public record SleepStateChangedEvent(@NotNull SleepState previous, @NotNull SleepState current) implements SEvent {

    public enum SleepState {
        AWAKE,
        FALLING_ASLEEP,
        SLEEPING,
        WAKING_UP
    }
}
