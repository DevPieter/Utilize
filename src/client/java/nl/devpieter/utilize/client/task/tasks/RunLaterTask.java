package nl.devpieter.utilize.client.task.tasks;

import nl.devpieter.utilize.client.task.ITask;
import nl.devpieter.utilize.client.task.enums.TickResult;
import nl.devpieter.utilize.utils.common.MathUtils;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class RunLaterTask implements ITask {

    private final Runnable runnable;

    private final int startDelayTicks;

    private boolean finished;
    private int tickCounter;

    public RunLaterTask(@NotNull Runnable runnable, @NotNull Duration startDelay) {
        this(runnable, MathUtils.durationToTicks(startDelay));
    }

    public RunLaterTask(@NotNull Runnable runnable, int startDelayTicks) {
        this.runnable = runnable;
        this.startDelayTicks = startDelayTicks;
    }

    @Override
    public TickResult tick() {
        if (finished) return TickResult.FINISHED;

        tickCounter++;
        if (tickCounter < startDelayTicks) {
            return TickResult.CONTINUE;
        }

        runnable.run();
        finished = true;

        return TickResult.FINISHED;
    }
}
