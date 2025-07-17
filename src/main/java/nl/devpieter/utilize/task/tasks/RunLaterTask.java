package nl.devpieter.utilize.task.tasks;

import nl.devpieter.utilize.task.interfaces.ITask;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class RunLaterTask implements ITask {

    private final Runnable runnable;

    private final int startDelayTicks;
    private final boolean resetOnFinish;

    private boolean finished;
    private int tickCounter;

    public RunLaterTask(@NotNull Runnable runnable, int startDelayTicks) {
        this(runnable, startDelayTicks, false);
    }

    public RunLaterTask(@NotNull Runnable runnable, @NotNull Duration startDelay) {
        this(runnable, startDelay, false);
    }

    public RunLaterTask(@NotNull Runnable runnable, @NotNull Duration startDelay, boolean resetOnFinish) {
        this(runnable, (int) (startDelay.toMillis() / 50), resetOnFinish);
    }

    public RunLaterTask(@NotNull Runnable runnable, int startDelayTicks, boolean resetOnFinish) {
        this.runnable = runnable;
        this.startDelayTicks = startDelayTicks;
        this.resetOnFinish = resetOnFinish;
    }

    @Override
    public void tick() {
        if (this.finished) return;

        this.tickCounter++;
        if (this.tickCounter < this.startDelayTicks) return;

        this.runnable.run();
        this.finished = true;
    }

    @Override
    public void reset() {
        this.finished = false;
        this.tickCounter = 0;
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public boolean resetOnFinish() {
        return this.resetOnFinish;
    }
}
