package duke.task;

/**
 * Task that must be completed before a set deadline.
 */
public class Deadline extends Task {

    /** Deadline of task. */
    private String by; // String containing deadline

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getBy() {
        return by;
    }
    
    public void setBy(String by) {
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toData(int taskId) {
        String dataLine = taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription() + "," + this.getBy();
        return dataLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}