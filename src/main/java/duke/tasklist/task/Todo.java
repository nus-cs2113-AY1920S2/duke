package duke.taskList.task;

import static duke.common.Constants.TODO;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T | " + super.toFile();
    }

    @Override
    public String type() {
        return TODO;
    }
}
