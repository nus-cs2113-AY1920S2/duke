public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns tick or X based on task completion */
    public String getStatusIcon() {
        // Return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
