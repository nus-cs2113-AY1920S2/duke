public class Task {

    /**
     * Name of task
     */
    private String name;

    /**
     * Number of task
     */
    private int taskNumber;

    /**
     * Whether task is done or not
     */
    private boolean isDone;

    /**
     * A Task object: something to be done
     * @param name name of task
     * @param taskNumber number of task
     */
    public Task(String name, int taskNumber) {
        this.name = name;
        this.taskNumber = taskNumber;
        isDone = false;
    }

    /**
     * Set task to done or not
     * @param isDone whether task is done or not
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Get task number
     * @return task number
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Get name of task
     * @return Task name
     */
    public String getName() {
        return name;
    }

    /**
     * Print task is specified format
     */
    public String toString() {
        return "    " + taskNumber + ". [" + getStatusIcon() + "] " + name;
    }

    /**
     * Get status icon of task
     * @return status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}
