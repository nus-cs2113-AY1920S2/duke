package duke.tasks;

import java.util.List;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        String description = this.description;
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides the default toString so that duke.tasks.Task gets printed in a specific format
     *
     * @return the formatted String to print
     */
    public String toString() {
        String symbol = getStatusIcon();
        String toPrint = String.format("[%s] %s", symbol, this.description );
        return toPrint;
    }

    public abstract void addIfMatchesKeyword(Task t, List<Task> foundTasks, String keyword);
}
