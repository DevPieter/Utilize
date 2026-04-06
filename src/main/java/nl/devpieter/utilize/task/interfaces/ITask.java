package nl.devpieter.utilize.task.interfaces;

import nl.devpieter.utilize.task.TaskManager;

/**
 * @deprecated Use {@link nl.devpieter.utilize.task.ITask} instead.
 */
@Deprecated(since = "1.1.3", forRemoval = true)
public interface ITask {

    TaskManager.TickResult tick();
}
