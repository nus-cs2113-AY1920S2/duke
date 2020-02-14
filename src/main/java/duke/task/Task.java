package duke.task;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean status) {
        this.isDone = true;
    }

    public abstract String getDetails();

    public String getTaskStatus() {
        String icon = isDone ? "\u2713" : "\u2718";
        return ("[" + icon + "] " + task);
    }
}
