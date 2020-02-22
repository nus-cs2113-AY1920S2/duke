package Duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, String status) {
        super(description);
        this.by = by;
        if (status.equals("1")) {
            this.markAsDone();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSaveFormat() {
        return(super.toSaveFormat() + "D|" + this.by);
    }
}