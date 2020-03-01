package duke.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskDescription = "deadline";
    }

    @Override
    public String getByDescription() {
        return (" (by: " + by + ")");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}