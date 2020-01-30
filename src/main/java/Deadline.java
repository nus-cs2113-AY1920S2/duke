public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }
}
