package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abstract class to represent a task
 */
public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        setDescription(description);
        setIsDone(false);
    }

    /**
     * @return Tick mark or X based on completion status of this task
     */
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Set the description
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set whether this task is done
     * @param isDone new done status
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Get if the task's associated dateTime is before the target dateTime
     * @param dateTime target dateTime
     * @return if the task's associated dateTime is before the target dateTime
     */
    public abstract boolean getIsBy(LocalDateTime dateTime);

    /**
     * Get if the task's associated dateTime is on the same day as the target date
     * @param date target date
     * @return if the task's associated dateTime is on the same day as the target date
     */
    public abstract boolean getIsOn(LocalDate date);

    /**
     * Check if the description contains a word
     * @param word target word
     * @return whether or not description contains target word
     */
    public boolean containsWord(String word) {
        return description.contains(word);
    }

    /**
     * get the string representation of this task
     * @return string representation of this task
     */
    public abstract String toFormattedString();
}
