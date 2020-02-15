package duke.task;

public class DeadLine extends Task {
    protected String by;

    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toOutput() {
        return "D | " + (isDone ? '1' : '0') + " | " + description
                + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
