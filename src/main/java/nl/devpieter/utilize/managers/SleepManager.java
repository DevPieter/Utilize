package nl.devpieter.utilize.managers;

import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.player.SleepStateChangedEvent;

public class SleepManager {

    private static SleepManager INSTANCE;

    private final Sees sees = Sees.getInstance();

    private SleepStateChangedEvent.SleepState currentState = SleepStateChangedEvent.SleepState.AWAKE;

    private SleepManager() {
    }

    public static SleepManager getInstance() {
        if (INSTANCE == null) INSTANCE = new SleepManager();
        return INSTANCE;
    }

    public void tick(boolean isSleeping, float sleepTimer) {
        SleepStateChangedEvent.SleepState previous = this.currentState;

        switch (this.currentState) {
            case AWAKE:
                if (isSleeping) this.currentState = SleepStateChangedEvent.SleepState.FALLING_ASLEEP;
                break;
            case FALLING_ASLEEP:
                if (!isSleeping) this.currentState = SleepStateChangedEvent.SleepState.AWAKE;
                else if (sleepTimer == 100) this.currentState = SleepStateChangedEvent.SleepState.SLEEPING;
                break;
            case SLEEPING:
                if (!isSleeping) this.currentState = SleepStateChangedEvent.SleepState.WAKING_UP;
                break;
            case WAKING_UP:
                if (!isSleeping) this.currentState = SleepStateChangedEvent.SleepState.AWAKE;
                else if (sleepTimer == 0) this.currentState = SleepStateChangedEvent.SleepState.SLEEPING;
                break;
        }

        if (previous == this.currentState) return;
        sees.call(new SleepStateChangedEvent(previous, this.currentState));
    }

    public SleepStateChangedEvent.SleepState getCurrentState() {
        return currentState;
    }
}
