package data.task;

public class DeadlineTask extends Task{
    private String taskDeadline;

    public DeadlineTask() {
    }

    public DeadlineTask(String taskDescription, String taskDeadline) {
        super(taskDescription, 'D');
        this.taskDeadline = taskDeadline;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }
}
