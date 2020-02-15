package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toOutput() {
        return "T | " + isDone + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
