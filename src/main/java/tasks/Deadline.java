package tasks;

/**
 * tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    protected String dueBy;

    public Deadline(TaskType category, String taskName, String dueBy) {
        super(category, taskName);
        this.category = 'D';
        this.dueBy = dueBy;
    }

    public String getDueBy() {
        return dueBy;
    }

    public void setDueBy(String dueBy) {
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return (super.toString() + " (by: " + dueBy +")" );
    }
}
