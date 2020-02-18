package TaskObjects;

public class Task {
    protected int taskNum;
    protected String description;
    protected String type;
    protected boolean isDone;

    private static int totalTask = 0;

    public Task(String description, String type) {
        totalTask++;
        this.taskNum = totalTask;
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public static int getTotalTask() {
        return totalTask;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" +getStatusIcon() + "]" + description;
    }

    public String stringToSave(){
        return type + "|" + (isDone?"0":"1") + "|" + description;
    }
}
