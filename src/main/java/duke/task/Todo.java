package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.isDone = false;
        super.taskType = "[T]";
    }

    public boolean isDone() {
        return super.isDone;
    }

    @Override
    public String toString(){
        return "[T] " + super.toString();
    }


}
