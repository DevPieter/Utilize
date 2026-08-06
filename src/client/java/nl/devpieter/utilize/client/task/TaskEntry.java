package nl.devpieter.utilize.client.task;

import nl.devpieter.utilize.client.task.enums.TickPhase;

public record TaskEntry(ITask task, TickPhase phase) {
}
