package data.task;

public class EventTask extends Task{
    private String taskStartTime;
    private String taskEndTime;

    public EventTask() {
    }

    public EventTask(int taskIndex, String taskDescription, String taskStartTime) {
        super(taskIndex, taskDescription, 'E');
        this.taskStartTime = taskStartTime;
        //this.taskEndTime = taskEndTime;
    }

    public EventTask(int taskIndex, String taskDescription, String taskStartTime, String taskEndTime) {
        super(taskIndex, taskDescription, 'E');
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }
}
