package nl.devpieter.utilize;

import nl.devpieter.utilize.task.TaskManager;
import nl.devpieter.utilize.task.interfaces.ITask;

public class MyOldTestTask implements ITask {

    @Override
    public TaskManager.TickResult tick() {
        return null;
    }
}
