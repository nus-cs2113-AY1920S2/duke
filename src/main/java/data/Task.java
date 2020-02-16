package data;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final String TASK_DONE_NOTATION = "1";
    protected final String TASK_NOTDONE_NOTATION = "0";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String[] getTaskData();
}
