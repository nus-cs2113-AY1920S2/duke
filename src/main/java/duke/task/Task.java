package duke.task;

/**
 * Represents a task in the task list
 */
public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "] "); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String formatResult() {
        return isDone + "|" + this.description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}