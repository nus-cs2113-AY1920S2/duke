package duke.tasks;

import java.time.LocalDate;

import static duke.utils.Constants.YES_ICON;
import static duke.utils.Constants.NO_ICON;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type; // to be assigned in subclasses
    protected LocalDate date; // only assigned for deadline and event

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getStatusIcon() {
        return (this.isDone ? YES_ICON : NO_ICON); // return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
    
    public void markAsDone() {
        this.isDone = true;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public abstract String getFileString();
}
