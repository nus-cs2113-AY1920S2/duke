package duke.tasks;


/**
 * Abstract class for Task.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskDescription;

    /**
     * Constructor for abstract Task class.
     * @param description String for description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskDescription = "task";
    }

    /**
     * To update and set isDone to true when task is marked done.
     */
    public void updateIsDone (){
        this.isDone = true;
    }

    /**
     * To indicate if task is done.
     * @return Tick symbol if done else Cross not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Formats tasks to display task messages to user.
     * @return String containing status and description of tasks.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}