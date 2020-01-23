public class Task {
    protected int taskNum;
    protected String description;
    protected boolean isDone;

    private static int totalTask = 0;

    public Task(String description) {
        totalTask++;
        this.taskNum = totalTask;
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = true;
    }

    public static int getTotalTask() {
        return totalTask;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}
