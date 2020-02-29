package duke.task;

import duke.DukeException;
import duke.task.Task;

/**
 * Represents an item to do in the task list
 */
public class Todo extends Task {

    /**
     * Constructor to create a new to do task.
     *
     * @param description description of the task
     * @throws DukeException throws an exception if the description or date of the task is empty
     */
    public Todo(String description) throws DukeException {
        super(description);

        if(description.isBlank()) {
            throw new DukeException();
        }
    }

    @Override
    public String formatResult() {
        return "t|" + super.formatResult();
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
