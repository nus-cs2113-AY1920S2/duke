package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " "); // Return x if task is done
    }

    public String getTask() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getTask();
    }

    public String toFileString() {
        return this.getStatusIcon() + "," + this.description;
    }
}