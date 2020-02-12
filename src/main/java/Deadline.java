public class Deadline extends Task {
    protected String description;
    protected boolean isDone;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description,boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String storeText() {
        return "[D]," + super.getStatus() + "," + super.getDescription() + "," + by;
    }

    @Override
    public String toString() {
        return "[D][ " + super.getStatusIcon() + " ] " + super.getDescription() + "(By: " + getBy() + ")";
    }
}