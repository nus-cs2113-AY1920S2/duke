package alie.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static final String INDENTATION = "      ";
    protected static final String MORE_INDENTATION = "        ";

    public Task (String name) {
        this.description = name;
        this.isDone = false;
    }

    public String getName() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setIsCompleted() {
        this.isDone = true;
    }

    public String getTaskInfo() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    public String justAddedText(int taskCount) {
        return ("Got it. I've added this task:" + System.lineSeparator() +
                MORE_INDENTATION + getTaskInfo() + System.lineSeparator() +
                INDENTATION + "Now you have " + (taskCount+1) + " tasks in the list.");
    }
}
