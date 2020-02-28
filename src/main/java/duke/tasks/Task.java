package duke.tasks;

import duke.exceptions.BadLineFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        setDescription(description);
        setIsDone(false);
    }

    // getStatusIcon() is from the website
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract boolean getIsBy(LocalDateTime dateTime);

    public abstract boolean getIsOn(LocalDate date);

    public abstract String toFormattedString();
}
