package duke.task;

import duke.format.DateTime;

/**
 * <h3>Deadline</h3>
 * An <b>Deadline</b> task refers to a type of task that the user wants to do <u>by</u> a certain datetime.
 * @see Task
 */
public class Deadline extends Task {
    protected DateTime deadline;

    /**
     * Constructs an <b>Deadline</b> task from the given <code>task</code> and <code>deadline</code>.
     *
     * @param task The <i>task description</i> of the <b>Deadline</b> task to be created
     * @param deadline The <i>deadline</i> of the <b>Deadline</b> task
     */
    public Deadline(String task, DateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Returns the <i>deadline</i> of the task.
     *
     * @return The <i>deadline</i> of the task
     */
    @Override
    public DateTime getDateTime() {
        return deadline;
    }

    /**
     * Returns the current task status of the task, specifically the <i>task type</i> (i.e. <b>Deadline</b>),
     * the current <i>done status</i>, the <i>task description</i> and the <i>deadline</i>.
     *
     * @return The task status of the task
     */
    @Override
    public String getTaskStatus() {
        return "[D]" + super.getTaskStatus() + " (by: " + deadline + ")";
    }
}
