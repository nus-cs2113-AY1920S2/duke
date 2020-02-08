package duke.task;

import duke.exception.DukeException;

public class Todo extends Task {
    protected  String typeIcon;

    public Todo(String description) throws DukeException {
        super(description);
        this.typeIcon = "[T]";
    }

    @Override
    public String toString() {
        return typeIcon + super.toString();
    }
}
