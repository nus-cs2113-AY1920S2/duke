package duke.task;

import duke.format.DateTime;

/**
 * <h3>To Do</h3>
 * A <b>To Do</b> task refers to a type of task that the user wants to do.
 * @see Task
 */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    /**
     * Returns the <i>datetime</i> information of the task.
     * <br> This is always <code>NULL</code> as a <b>To Do</b> task does not have any datetime information.
     *
     * @return <code>NULL</code>
     */
    @Override
    public DateTime getDateTime() {
        return null;
    }

    /**
     * Returns the current task status of the task, specifically the <i>task type</i> (i.e. <b>To Do</b>),
     * the current <i>done status</i> and the <i>task description</i>.
     *
     * @return The task status of the task
     */
    @Override
    public String getTaskStatus() {
        return "[T]" + super.getTaskStatus();
    }
}
