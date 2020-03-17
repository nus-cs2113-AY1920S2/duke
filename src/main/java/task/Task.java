package task;


public abstract class Task {

    protected TaskType taskType;
    protected boolean isDone;
    protected String taskDetails;
    protected String taskDate;
    protected String taskTime;

    /**
     * Constructor for Task class.
     * @param taskType type of task, T for todo, E for event, D for deadline
     * @param isDone the status of completion of a task
     * @param taskDetails the details of the task itself
     */
    public Task(TaskType taskType, boolean isDone, String taskDetails) {
        this.taskType = taskType;
        this.isDone = isDone;
        this.taskDetails = taskDetails;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getTaskTime() { return taskTime; }

    public String getTaskDetails() {
        return this.taskDetails;
    }

    /**
     * Mark the particular task as complete.
     * @param numberOfIncompleteTasks the total number of incomplete tasks for display purposes
     */
    public void markTaskAsDone(int numberOfIncompleteTasks) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            this.printListMessage();
            System.out.println("Total number of incomplete tasks: " + numberOfIncompleteTasks);
    }

    /**
     * Convert isDone from boolean to string for display purpose.
     * @param isDone isDone status of the task
     * @return Display value of the isDone status in the form of "Done" or "Not Done"
     */
    public String convertIsDoneDisplay(boolean isDone) {
        String isDoneDisplay;
        if (isDone) {
            isDoneDisplay = "Done";
        } else {
            isDoneDisplay = "Not Done";
        }
        return isDoneDisplay;
    }

    /**
     * Print a confirmation message when a task is created.
     */
    public abstract void printCreateMessage();

    /**
     * Print the task in the format when the list command is called.
     */
    public abstract void printListMessage();
}