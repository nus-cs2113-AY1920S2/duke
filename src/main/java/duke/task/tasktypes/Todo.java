package duke.task.tasktypes;

/**
 * A class representing a todo task.
 */
public class Todo extends Task {

    public Todo (String description) {
        super(description);

        this.taskType = TaskType.T;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", taskType, super.toString());
    }
}
