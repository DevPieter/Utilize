package nl.devpieter.utilize.task;

import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public final class TaskManager {

    private static final TaskManager INSTANCE = new TaskManager();

    private final OldTaskManager oldTaskManager;

    private final List<nl.devpieter.utilize.task.models.TaskEntry> pendingTasks = new ArrayList<>();
    private final EnumMap<nl.devpieter.utilize.task.enums.TickPhase, List<ITask>> taskMap = new EnumMap<>(nl.devpieter.utilize.task.enums.TickPhase.class);

    private TaskManager() {
        oldTaskManager = new OldTaskManager();

        taskMap.put(nl.devpieter.utilize.task.enums.TickPhase.PLAYER_HEAD, new ArrayList<>());
        taskMap.put(nl.devpieter.utilize.task.enums.TickPhase.PLAYER_TAIL, new ArrayList<>());
        taskMap.put(nl.devpieter.utilize.task.enums.TickPhase.CLIENT_HEAD, new ArrayList<>());
        taskMap.put(nl.devpieter.utilize.task.enums.TickPhase.CLIENT_TAIL, new ArrayList<>());
    }

    public static TaskManager getInstance() {
        return INSTANCE;
    }

    @Deprecated(since = "1.1.3", forRemoval = true)
    public void addTask(nl.devpieter.utilize.task.interfaces.ITask task) {
        oldTaskManager.addTask(task);
    }

    @Deprecated(since = "1.1.3", forRemoval = true)
    public void addTask(nl.devpieter.utilize.task.interfaces.ITask task, TickPhase phase) {
        oldTaskManager.addTask(task, phase);
    }

    @Deprecated(since = "1.1.3", forRemoval = true)
    public void addTask(nl.devpieter.utilize.task.interfaces.ITask task, nl.devpieter.utilize.task.enums.TickPhase phase) {
        oldTaskManager.addTask(task, toOldPhase(phase));
    }

    @Deprecated(since = "1.1.3", forRemoval = true)
    public void removeTask(nl.devpieter.utilize.task.interfaces.ITask task) {
        oldTaskManager.removeTask(task);
    }

    public void addTask(ITask task) {
        addTask(task, nl.devpieter.utilize.task.enums.TickPhase.PLAYER_TAIL);
    }

    public void addTask(ITask task, nl.devpieter.utilize.task.enums.TickPhase phase) {
        pendingTasks.add(new nl.devpieter.utilize.task.models.TaskEntry(task, phase));
    }

    public void removeTask(ITask task) {
        pendingTasks.removeIf(entry -> entry.task() == task);

        for (List<ITask> tasks : taskMap.values()) {
            tasks.remove(task);
        }
    }

    @ApiStatus.Internal
    public void beginTick() {
        oldTaskManager.beginTick();
        flushPendingTasks();
    }

    @ApiStatus.Internal
    public void tick(nl.devpieter.utilize.task.enums.TickPhase phase) {
        oldTaskManager.tick(toOldPhase(phase));

        List<ITask> tasks = taskMap.get(phase);
        if (tasks == null || tasks.isEmpty()) return;

        List<ITask> toRemove = new ArrayList<>();

        for (ITask task : tasks) {
            nl.devpieter.utilize.task.enums.TickResult result = task.tick();

            if (result == nl.devpieter.utilize.task.enums.TickResult.FINISHED) {
                toRemove.add(task);
            } else if (result == nl.devpieter.utilize.task.enums.TickResult.REQUEUE) {
                toRemove.add(task);
                addTask(task, phase);
            }
        }

        tasks.removeAll(toRemove);
    }

    private void flushPendingTasks() {
        if (pendingTasks.isEmpty()) return;

        for (nl.devpieter.utilize.task.models.TaskEntry entry : pendingTasks) {
            taskMap.get(entry.phase()).add(entry.task());
        }

        pendingTasks.clear();
    }

    @Deprecated(since = "1.1.3", forRemoval = true)
    public enum TickPhase {
        PLAYER_HEAD,
        PLAYER_TAIL,
        CLIENT_HEAD,
        CLIENT_TAIL
    }

    @Deprecated(since = "1.1.3", forRemoval = true)
    public enum TickResult {
        CONTINUE,
        FINISHED,
        REQUEUE
    }

    @Deprecated(since = "1.1.3", forRemoval = true)
    public record TaskEntry(nl.devpieter.utilize.task.interfaces.ITask task, TickPhase phase) {
    }

    private TickPhase toOldPhase(nl.devpieter.utilize.task.enums.TickPhase phase) {
        return switch (phase) {
            case PLAYER_HEAD -> TickPhase.PLAYER_HEAD;
            case PLAYER_TAIL -> TickPhase.PLAYER_TAIL;
            case CLIENT_HEAD -> TickPhase.CLIENT_HEAD;
            case CLIENT_TAIL -> TickPhase.CLIENT_TAIL;
        };
    }
}
