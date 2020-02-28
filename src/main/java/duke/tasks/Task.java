package duke.tasks;

import duke.exceptions.BadLineFormatException;

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

    public abstract boolean getIsBy(LocalDateTime dateTime);

    public abstract boolean getIsOn(LocalDate date);

    public boolean containsWord(String word) {
        return description.contains(word);
    }

    /**
     * get the string representation of this task
     * @return string representation of this task
     */
    public abstract String toFormattedString();
}
