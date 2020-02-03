public class Task {
    protected String description;
    protected boolean isDone;

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
        return ("Got it. I've added this task:\n" +
                TaskManager.MORE_INDENTATION + getTaskInfo() + "\n " +
                TaskManager.INDENTATION +
                "Now you have " + (taskCount+1) + " tasks in the list.");
    }
}
