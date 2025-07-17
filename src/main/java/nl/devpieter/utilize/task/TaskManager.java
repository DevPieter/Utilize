package nl.devpieter.utilize.task;

import nl.devpieter.utilize.task.interfaces.ITask;

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

    public static void shutdown() {
        if (INSTANCE == null) return;

        INSTANCE.tasks.clear();
        INSTANCE = null;
    }

    public void addTask(ITask task) {
        this.tasks.add(task);
    }

    public void removeTask(ITask task) {
        this.tasks.remove(task);
    }

    public void tick() {
        for (ITask task : this.tasks) {
            task.tick();
            if (!task.isFinished()) continue;

            if (task.resetOnFinish()) task.reset();
            else this.removeTask(task);
        }
    }
}
