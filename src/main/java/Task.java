public class Task {
    protected static final String YES_ICON = "\u2713";
    protected static final String NO_ICON = "\u2718";
    
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON); // return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public void markAsDone() {
        isDone = true;
    }
}
