package data.task;

public class EventTask extends Task{
    private String taskStartTime;
    private String taskEndTime;

    public EventTask() {
    }

    public EventTask(String taskDescription, String taskStartTime) {
        super(taskDescription, 'E');
        this.taskStartTime = taskStartTime;
        //this.taskEndTime = taskEndTime;
    }

    public EventTask(String taskDescription, String taskStartTime, String taskEndTime) {
        super(taskDescription, 'E');
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
