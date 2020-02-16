package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns tick or X symbols
     *
     * @return symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[" + getStatusIcon() + "]  " + this.description);
    }

    public String convertToData() {
        int isDoneAsInt = isDone ? 1 : 0;
        return String.format(isDoneAsInt + "|" + this.description);
    }
}
