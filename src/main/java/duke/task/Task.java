package duke.task;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean status) {
        isDone = status;
    }

    public abstract String getDetails();

    public String getTaskStatus() {
        String statusIcon = isDone ? "\u2713" : "\u2718";
        return ("[" + statusIcon + "] " + task);
    }
}
