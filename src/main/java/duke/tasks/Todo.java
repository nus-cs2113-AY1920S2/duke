package duke.tasks;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description.trim());

        if(description.isBlank()) {
            throw new DukeException();
        }
    }

    @Override
    public String saveFormat() {
        return "t//" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
