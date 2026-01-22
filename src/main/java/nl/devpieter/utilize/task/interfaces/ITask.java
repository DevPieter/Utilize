package nl.devpieter.utilize.task.interfaces;

import nl.devpieter.utilize.task.TaskManager;

public interface ITask {

    TaskManager.TickResult tick();
}
