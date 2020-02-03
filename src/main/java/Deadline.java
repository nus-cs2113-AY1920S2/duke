public class Deadline extends Task {
    protected String by;

    public Deadline(String action, String by) {
        super(action);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
