package duke.tasks;

import java.time.LocalDate;

import static duke.utils.Constants.YES_ICON;
import static duke.utils.Constants.NO_ICON;

/**
 * Objects that stores the task content, date, status, and type.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type; // to be assigned in subclasses
    protected LocalDate date; // only assigned for deadline and event

    /**
     * Defines the constructor.
     * Fills in the task content and initializes the status of task as undone.
     *
     * @param description Content of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the corresponding icon, tick or cross, for the status of the task.
     *
     * @return Tick for done tasks; Cross of undone tasks.
     */
    public String getStatusIcon() {
        return (this.isDone ? YES_ICON : NO_ICON);
    }

    /**
     * Specifies the format when printing this task object.
     * 
     * @return Formatted string: [tick/cross] task content.
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the date of the task: deadline date, event date.
     * 
     * @return Date of the task.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the task content.
     * 
     * @return Task content.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Specifies the format of string that will be written into the txt file.
     * 
     * @return Formatted string.
     */
    public abstract String getFileString();
}
