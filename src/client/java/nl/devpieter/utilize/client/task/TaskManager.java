package nl.devpieter.utilize.client.task;

import nl.devpieter.utilize.client.task.enums.TickPhase;
import nl.devpieter.utilize.client.task.enums.TickResult;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public final class TaskManager {

    private static final TaskManager INSTANCE = new TaskManager();

    private final List<TaskEntry> pendingTasks = new ArrayList<>();
    private final EnumMap<TickPhase, List<ITask>> taskMap = new EnumMap<>(TickPhase.class);

    private TaskManager() {
        taskMap.put(TickPhase.PLAYER_HEAD, new ArrayList<>());
        taskMap.put(TickPhase.PLAYER_TAIL, new ArrayList<>());
        taskMap.put(TickPhase.CLIENT_HEAD, new ArrayList<>());
        taskMap.put(TickPhase.CLIENT_TAIL, new ArrayList<>());
    }

    public static TaskManager getInstance() {
        return INSTANCE;
    }

    public void addTask(ITask task) {
        addTask(task, TickPhase.PLAYER_TAIL);
    }

    public void addTask(ITask task, TickPhase phase) {
        pendingTasks.add(new TaskEntry(task, phase));
    }

    public void removeTask(ITask task) {
        pendingTasks.removeIf(entry -> entry.task() == task);

        for (List<ITask> tasks : taskMap.values()) {
            tasks.remove(task);
        }
    }

    public void beginTick() {
        flushPendingTasks();
    }

    public void tick(TickPhase phase) {
        List<ITask> tasks = taskMap.get(phase);
        if (tasks == null || tasks.isEmpty()) return;

        List<ITask> toRemove = new ArrayList<>();

        for (ITask task : tasks) {
            TickResult result = task.tick();

            if (result == TickResult.FINISHED) {
                toRemove.add(task);
            } else if (result == TickResult.REQUEUE) {
                toRemove.add(task);
                addTask(task, phase);
            }
        }

        tasks.removeAll(toRemove);
    }

    private void flushPendingTasks() {
        if (pendingTasks.isEmpty()) return;

        for (TaskEntry entry : pendingTasks) {
            taskMap.get(entry.phase()).add(entry.task());
        }

        pendingTasks.clear();
    }
}
