package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeNullDescriptionException;

import static duke.util.Constants.TODO_ICON;

public class Todo extends Task {
    protected String typeIcon;

    public Todo(String description) throws DukeNullDescriptionException {
        super(description);
        this.typeIcon = TODO_ICON;
    }

    public String getIcon() {
        return typeIcon;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString();
    }
}
