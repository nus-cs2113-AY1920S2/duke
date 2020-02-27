package tasks;

/**
 * <h1>Deadline</h1>
 * Deadline tasks are tasks that need to be done before a specific date/time
 * e.g., submit report /by Monday
 * Contains Deadline-specific descriptions
 */
public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String taskName, String dueBy) {
        super(taskName);
        this.taskType = 'D';
        this.dueBy = dueBy;
    }

    /**
     * Not yet implemented: A branch-8 task (TODO)
     * Gets the 'due by' date of the deadline
     * @return the date as a String
     */
    public String getDueBy() {
        return dueBy;
    }

    /**
     * Not yet implemented: A branch-8 task (TODO)
     * Sets the deadline (date) of the task
     * @param dueBy the given date as a String
     */
    public void setDueBy(String dueBy) {
        this.dueBy = dueBy;
    }

    /**
     * Converts the Deadline task to a String-readable format
     * @return a String containing the task details, includes 'due by' date
     */
    @Override
    public String toString() {
        return (super.toString() + " (by: " + dueBy +")" );
    }
}
