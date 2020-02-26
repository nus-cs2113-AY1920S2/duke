package duke.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "T," + done + "," + description;
    }
}
