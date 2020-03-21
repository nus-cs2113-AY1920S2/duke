package duke.task;

/**
 * Class representing a Todo Task.
 */
public class Todo extends Task {

    protected boolean isDone;

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
