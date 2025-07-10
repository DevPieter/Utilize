package nl.devpieter.utilize.managers;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static TaskManager INSTANCE;

    private final List<ITask> tasks = new ArrayList<>();

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (INSTANCE == null) INSTANCE = new TaskManager();
        return INSTANCE;
    }

    public void addTask(ITask task) {
        this.tasks.add(task);
    }

    public void removeTask(ITask task) {
        this.tasks.remove(task);
    }

    public void tick() {
        for (ITask task : new ArrayList<>(tasks)) {
            task.tick();
            if (task.isFinished()) removeTask(task);
        }
    }

    public static interface ITask {
        void tick();

        void reset();

        boolean isFinished();
    }

    public static class RunOnceTask implements ITask {

        private final Runnable runnable;
        private final int startDelay;

        private boolean finished;
        private int tickCounter;

        public RunOnceTask(Runnable runnable, int startDelay) {
            this.runnable = runnable;
            this.startDelay = startDelay;
            this.tickCounter = 0;
        }

        public void tick() {
            if (finished) return;

            tickCounter++;
            if (tickCounter < startDelay) return;

            runnable.run();
            finished = true;
        }

        public void reset() {
            this.finished = false;
            this.tickCounter = 0;
        }

        public boolean isFinished() {
            return finished;
        }
    }
}
