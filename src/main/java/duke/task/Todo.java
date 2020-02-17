package duke.task;

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
