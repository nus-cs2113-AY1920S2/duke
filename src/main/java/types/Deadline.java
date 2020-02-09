package types;

public class Deadline extends Task {

    /**
     * When the deadline is by
     */
    protected String by;

    /**
     * A task with a specific deadline
     * @param description what the task is
     * @param taskNumber what number the task is on our list
     * @param by when the task needs to be done by
     */
    public Deadline(String description, int taskNumber, String by) {
        super(description, taskNumber);
        this.by = by;
    }

    /**
     * The task in string form
     * @return string of the task with the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
