package duke.task;

public class Todo extends Task {
    public Todo (int taskID, String description, boolean isDone) {
        super (taskID, description, isDone);
    }

    public String getTaskType () {
        return "[T]";
    }

    @Override
    public String toString () {
        return (super.taskID + 1) + ". [T]" + super.getStatusIcon() + " " + super.description;
    }
}
