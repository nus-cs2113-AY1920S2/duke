public class Task {
    protected String taskName;
    protected boolean isDone;
    protected int taskId;
    protected static int totalNumOfTasks = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskId = ++this.totalNumOfTasks;
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                String.format("[✓] %s", this.taskName));
    }

    public int getTaskId() {
        return taskId;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public static int getTotalNumOfTasks() {
        return totalNumOfTasks;
    }
}
