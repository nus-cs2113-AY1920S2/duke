/**
 * Contains all the information needed to construct a Task object.
 */

public class Task {

    /**
     * Descrption of the task.
     */

    protected String taskName;

    /**
     * Status of completion of the task.
     */

    protected boolean isDone;

    /**
     * The task index number.
     */

    protected int taskId;

    /**
     * The total number of tasks that has been inputted by the user.
     */

    protected static int totalNumOfTasks = 0;

    /**
     * Constructor for Task.
     * @param taskName description of the task as stated by the user.
     */

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskId = ++this.totalNumOfTasks;
    }

    /**
     * To mark a task as completed.
     */

    public void setDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                String.format("[✓] %s", this.taskName));
    }

    /**
     * To get the task index number.
     * @return taskId.
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Gives the status of completion of the task.
     * @return status of completion of the task.
     */

    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Gives the total number of tasks that the user has inputted so far.
     * @return total number of tasks.
     */

    public static int getTotalNumOfTasks() {
        return totalNumOfTasks;
    }
}
