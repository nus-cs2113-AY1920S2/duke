package duke.task;

import java.time.LocalDate;

/**
 * Task class to store the information of the task that user inputted,
 * also a superclass for types of Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor given description provided by user, all tasks are
     * automatically set to undone
     *
     * @param description information of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to get the symbol of tick and cross which stand for done and undone
     *
     * @return tick when the task is done or cross when task is undone
     */
    public String getStatusIcon() {
        if (isDone) {
            return "✓";
        } else {
            return "✗"; //return tick or X symbols
        }
    }

    /**
     * Method to get the first letter of the task's type used later in
     * subclasses
     *
     * @return empty string because this class do not have any types
     */
    public String getType() {
        return "";
    }

    /**
     * Method to get the description of a task
     *
     * @return description description's string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to check if the task is done or not, used for decoding in the
     * data file
     *
     * @return 1 if done, 0 if undone
     */
    public int getIsDone() {
        if (isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Method to get the time format of the task
     *
     * @return date that could store in the data file
     */
    public LocalDate getTime() {
        return LocalDate.parse("1998-01-16");
    }

    /**
     * Method to get the format of the time which is easily read by human
     *
     * @return string of the date
     */
    public String getTimeFormatted() {
        return "";
    }

    /**
     * Method to mark a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overriding of the default string's method
     *
     * @return format of the task described
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
