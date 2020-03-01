package Task;

/**
 * Represents a Task in the list kept by Duke
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static int numberOfTasksInList;
    /**
     * Creates a new Task
     * Default value for isDone is false
     * Increments the total number of Task in the list
     * @param description description of Task provided by user
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasksInList++;
    }

    /**
     * Return the current total number of Task in the TaskList
     * @return number of task in the list
     */
    public static int getNumberOfTasksInList() {
        return numberOfTasksInList;
    }

    /**
     * Decrement the number of Tasks in List
     */
    public static void reduceNumberOfTaskInList(){
        numberOfTasksInList--;
    }

    /**
     * Returns an icon corresponding to the status of Task
     * @return tick or cross icon
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Sets the boolean isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of Task
     * @return description of Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns String representing the boolean isDone
     * @return string representing boolean isDone
     */
    public String isDone() {
        return (isDone ? "Y" : "N");
    }

    @Override
    public abstract String toString();
    public abstract String getEventType();
    public abstract String getTaskTime();
    public abstract String getDoneResponseMessage(int itemIndexRequested);
}
