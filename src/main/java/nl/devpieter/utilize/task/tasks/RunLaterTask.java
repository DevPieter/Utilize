package nl.devpieter.utilize.task.tasks;

import nl.devpieter.utilize.task.TaskManager;
import nl.devpieter.utilize.task.interfaces.ITask;
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
    public TaskManager.TickResult tick() {
        if (finished) return TaskManager.TickResult.FINISHED;

        tickCounter++;
        if (tickCounter < startDelayTicks) {
            return TaskManager.TickResult.CONTINUE;
        }

        runnable.run();
        finished = true;

        return TaskManager.TickResult.FINISHED;
    }
}
