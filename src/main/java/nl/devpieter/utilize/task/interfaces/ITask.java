package nl.devpieter.utilize.task.interfaces;

public interface ITask {

    void tick();

    void reset();

    boolean isFinished();

    default boolean resetOnFinish() {
        return false;
    }
}
