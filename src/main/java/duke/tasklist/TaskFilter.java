package duke.tasklist;

import duke.tasks.Task;

@FunctionalInterface
public interface TaskFilter {
    boolean filter(Task task);
}
