package nl.devpieter.utilize.managers;

import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.enums.SleepState;
import nl.devpieter.utilize.events.SleepStateChangedEvent;

public class SleepManager {

    private static SleepManager INSTANCE;

    private final Sees sees = Sees.getInstance();

    private SleepState currentState = SleepState.AWAKE;

    private SleepManager() {
    }

    public static SleepManager getInstance() {
        if (INSTANCE == null) INSTANCE = new SleepManager();
        return INSTANCE;
    }

    public void tick(boolean isSleeping, float sleepTimer) {
        SleepState previous = this.currentState;

        switch (this.currentState) {
            case AWAKE:
                if (isSleeping) this.currentState = SleepState.FALLING_ASLEEP;
                break;
            case FALLING_ASLEEP:
                if (!isSleeping) this.currentState = SleepState.AWAKE;
                else if (sleepTimer == 100) this.currentState = SleepState.SLEEPING;
                break;
            case SLEEPING:
                if (!isSleeping) this.currentState = SleepState.WAKING_UP;
                break;
            case WAKING_UP:
                if (!isSleeping) this.currentState = SleepState.AWAKE;
                else if (sleepTimer == 0) this.currentState = SleepState.SLEEPING;
                break;
        }

        if (previous == this.currentState) return;
        sees.call(new SleepStateChangedEvent(previous, this.currentState));
    }

    public SleepState getCurrentState() {
        return currentState;
    }
}
