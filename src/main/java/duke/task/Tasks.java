package duke.task;

public abstract class Tasks {

    public String description;
    protected boolean isDone;
    protected int number;
    protected String type;

    private static int currTaskNumber = 1;

    public Tasks(String description, String type) {
        this.description = description;
        this.type = type;
        this.number = currTaskNumber;
        currTaskNumber += 1;

    }

    public Tasks(String description) {
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone () {
        this.isDone = true;
    }
    public int getTaskNumber() {
        return number;
    }
    public void setTaskNumber() {
        this.number = number;
    }
    public abstract String getFileContents();


    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description;
    }



}
