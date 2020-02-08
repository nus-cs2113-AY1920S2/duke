package task;
import static util.Constants.*;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? yesIcon : noIcon); //return tick or X symbols
    }

    public String getTaskDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getTaskDescription();
    }
}