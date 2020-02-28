package duke.task;

import duke.format.DateTime;

/**
 * <h3>Event</h3>
 * An <b>Event</b> task refers to a type of task that the user wants to do <u>at</u> a certain datetime.
 * @see Task
 */
public class Event extends Task {
    protected DateTime datetime;

    /**
     * Constructs an <b>Event</b> task from the given <code>task</code> and <code>datetime</code>.
     *
     * @param task The <i>task description</i> of the <b>Event</b> task to be created
     * @param datetime The <i>datetime</i> information of the <b>Event</b> task
     */
    public Event(String task, DateTime datetime) {
        super(task);
        this.datetime = datetime;
    }

    /**
     * Returns the <i>datetime</i> information of the task.
     *
     * @return The <i>datetime</i> information of the task
     */
    @Override
    public DateTime getDateTime() {
        return datetime;
    }

    /**
     * Returns the current task status of the task, specifically the <i>task type</i> (i.e. <b>Event</b>),
     * the current <i>done status</i>, the <i>task description</i> and the <i>datetime</i> information.
     *
     * @return The task status of the task
     */
    @Override
    public String getTaskStatus() {
        return "[E]" + super.getTaskStatus() + " (at: " + datetime + ")";
    }
}
