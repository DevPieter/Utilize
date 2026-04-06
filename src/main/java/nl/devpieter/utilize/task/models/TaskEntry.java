package nl.devpieter.utilize.task.models;

import nl.devpieter.utilize.task.ITask;
import nl.devpieter.utilize.task.enums.TickPhase;

public record TaskEntry(ITask task, TickPhase phase) {
}
