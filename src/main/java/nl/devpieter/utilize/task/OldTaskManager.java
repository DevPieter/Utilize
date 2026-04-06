package nl.devpieter.utilize.task;

import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

@ApiStatus.Internal
public final class OldTaskManager {

    private final List<TaskManager.TaskEntry> pendingTasks = new ArrayList<>();
    private final EnumMap<TaskManager.TickPhase, List<nl.devpieter.utilize.task.interfaces.ITask>> taskMap = new EnumMap<>(TaskManager.TickPhase.class);

    public OldTaskManager() {
        taskMap.put(TaskManager.TickPhase.PLAYER_HEAD, new ArrayList<>());
        taskMap.put(TaskManager.TickPhase.PLAYER_TAIL, new ArrayList<>());
        taskMap.put(TaskManager.TickPhase.CLIENT_HEAD, new ArrayList<>());
        taskMap.put(TaskManager.TickPhase.CLIENT_TAIL, new ArrayList<>());
    }

    public void addTask(nl.devpieter.utilize.task.interfaces.ITask task) {
        addTask(task, TaskManager.TickPhase.PLAYER_TAIL);
    }

    public void addTask(nl.devpieter.utilize.task.interfaces.ITask task, TaskManager.TickPhase phase) {
        pendingTasks.add(new TaskManager.TaskEntry(task, phase));
    }

    public void removeTask(nl.devpieter.utilize.task.interfaces.ITask task) {
        pendingTasks.removeIf(entry -> entry.task() == task);

        for (List<nl.devpieter.utilize.task.interfaces.ITask> tasks : taskMap.values()) {
            tasks.remove(task);
        }
    }

    @ApiStatus.Internal
    public void beginTick() {
        flushPendingTasks();
    }

    @ApiStatus.Internal
    public void tick(TaskManager.TickPhase phase) {
        List<nl.devpieter.utilize.task.interfaces.ITask> tasks = taskMap.get(phase);
        if (tasks == null || tasks.isEmpty()) return;

        List<nl.devpieter.utilize.task.interfaces.ITask> toRemove = new ArrayList<>();

        for (nl.devpieter.utilize.task.interfaces.ITask task : tasks) {
            TaskManager.TickResult result = task.tick();

            if (result == TaskManager.TickResult.FINISHED) {
                toRemove.add(task);
            } else if (result == TaskManager.TickResult.REQUEUE) {
                toRemove.add(task);
                addTask(task, phase);
            }
        }

        tasks.removeAll(toRemove);
    }


    private void flushPendingTasks() {
        if (pendingTasks.isEmpty()) return;

        for (TaskManager.TaskEntry entry : pendingTasks) {
            taskMap.get(entry.phase()).add(entry.task());
        }

        pendingTasks.clear();
    }
}
