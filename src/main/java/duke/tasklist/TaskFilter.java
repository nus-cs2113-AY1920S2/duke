package duke.tasklist;

import duke.tasks.Task;

/**
 * Functional interface used to specify what to filter task based on
 */
@FunctionalInterface
public interface TaskFilter {
    boolean filter(Task task);
}
