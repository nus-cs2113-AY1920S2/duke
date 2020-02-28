package duke.taskList.task;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(){

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "/" : " "); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTime() {
        return null;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toFile() {
        return ((isDone ? "1" : "0") + " | " + this.description);
    }

    public String type() {
        return null;
    }
}
