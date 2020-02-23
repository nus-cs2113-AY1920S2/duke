package duke.tasks;

import duke.tasks.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {

        return ("[T]");
    }

}
