public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        setDescription(description);
        setIsDone(false);
    }

    // From the website
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

}
