public class Deadline extends Task {

    protected String by;

    public Deadline(String description, int taskNumber, String by) {
        super(description, taskNumber);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
