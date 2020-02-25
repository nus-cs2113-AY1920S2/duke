package duke.task;

import duke.exception.DukeNullDescriptionException;

import static duke.util.Constants.NO_ICON;
import static duke.util.Constants.YES_ICON;

/**
 * This is the Task class to serve as a parent class for all types of tasks.
 *
 * @author A11riseforme
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor, set isDone to false by default as newly added tasks are all not done by its nature.
     *
     * @param description the description of the task
     * @throws DukeNullDescriptionException exception is thrown if description is empty.
     */
    public Task(String description) throws DukeNullDescriptionException {
        if (description.length() > 0) {
            this.description = description;
            this.isDone = false;
        } else {
            throw new DukeNullDescriptionException();
        }
    }

    /**
     * Get the icon representation for the task status. `[v]` for not done, `[v]` for done.
     *
     * @return a String of icon.
     */
    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON);
    }

    /**
     * Cast the task to String type by returning a string of its description
     *
     * @return the description of the task in String.
     */
    public String getTaskDescription() {
        return description;
    }

    /**
     * Set the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Cast the task to String type when called by System.out.println() or other similar methods.
     *
     * @return the String printout of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getTaskDescription();
    }
}