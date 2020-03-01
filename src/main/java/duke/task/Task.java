package duke.task;

import duke.format.DateTime;

import static duke.format.TextFormatter.CHECK_ICON;
import static duke.format.TextFormatter.CROSS_ICON;

/**
 * <h3>Task</h3>
 * A task is defined by the user and is categorised into 3 types: <b>To Do</b>, <b>Deadline</b> and <b>Event</b> tasks.
 * <br> A general task contains the information of the <i>task description</i> and <i>done status</i>.
 * @see ToDo
 * @see Deadline
 * @see Event
 */
public abstract class Task {
    protected String task; // The task description
    protected boolean isDone; // The done status of the task

    /**
     * Constructs a task from the given <code>task</code>. The <i>done status</i> is set to <code>FALSE</code>
     * by default.
     *
     * @param task The <i>task description</i> of the task to be created
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Returns the <i>task description</i> of the task.
     *
     * @return The <i>task description</i> of the task
     */
    public String getTask() {
        return task;
    }

    /**
     * Returns the <i>done status</i> of the task.
     *
     * @return The <i>done status</i> of the task
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the <i>datetime</i> information of the task.
     * <br> If the <i>datetime</i> information is absent, <code>NULL</code> is returned.
     *
     * @return The <i>datetime</i> information of the task
     */
    public abstract DateTime getDateTime();

    /**
     * Sets the <i>done status</i> of the task to the <code>doneStatus</code>.
     *
     * @param doneStatus The <i>done status</i> of the task to be set
     */
    public void setDone(boolean doneStatus) {
        isDone = doneStatus;
    }

    /**
     * Returns the current task status of the task, specifically the current <i>done status</i> and
     * the <i>task description</i>.
     *
     * @return The task status of the task
     */
    public String getTaskStatus() {
        String statusIcon = isDone ? CHECK_ICON : CROSS_ICON;
        return ("[" + statusIcon + "] " + task);
    }
}
