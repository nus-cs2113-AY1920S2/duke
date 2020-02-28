package duke.tasks;

import duke.DukeException;

/**
 * Represents a task that needs to be done.
 */
public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description.trim());

        if(description.isBlank()) {
            throw new DukeException();
        }
    }

    /**
     * Format which the tasks are saved into the .txt file.
     *
     * @return String with the save format
     */
    @Override
    public String saveFormat() {
        return "t//" + super.saveFormat();
    }

    /**
     * Format which the tasks are printed.
     *
     * @return String with the print format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
