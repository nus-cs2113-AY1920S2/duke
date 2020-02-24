package duke.task;

import duke.task.Task;

/**
 * Represents an item to do in the task list
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatResult() {
        return "t|" + super.formatResult();
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
