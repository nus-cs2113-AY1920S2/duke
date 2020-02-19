package commands;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int listCount = 0;


    protected String taskDescription;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskDescription = "task";
        listCount += 1;
    }

    public void updateIsDone (){
        this.isDone = true;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getDescription(){
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean isDone() {
        return isDone;
    }

    public String getAtDescription() {
        return null;
    }

    public String getByDescription() {
        return null;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}