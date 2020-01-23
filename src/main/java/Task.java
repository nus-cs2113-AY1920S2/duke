public class Task {
    public int taskID;
    public String description;
    public boolean isDone;

    public Task(int taskID, String description, boolean isDone) {
        this.taskID = taskID;
        this.description = description;
        this.isDone = isDone;
    }

    public void setStatus() {
        this.isDone = true; // mark as done
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
}
