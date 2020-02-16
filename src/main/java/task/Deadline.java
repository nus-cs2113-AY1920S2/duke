package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String convertToData() {
        int isDoneAsInt = isDone ? 1 : 0;
        return String.format("D|" + isDoneAsInt + "|" + this.description + "|" + this.by);
    }
}