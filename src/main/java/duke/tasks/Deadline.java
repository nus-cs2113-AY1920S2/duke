package duke.tasks;

public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    public Deadline(String description, String dueDateTime, boolean isDone) {
        this(description, dueDateTime);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }

    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "D," + done + "," + description + "," + dueDateTime;
    }
}
