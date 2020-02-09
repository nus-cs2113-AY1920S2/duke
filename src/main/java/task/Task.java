package task;

public class Task {
    private static int taskNum = 0;
    private String taskStatus;
    private String taskName;
    protected String taskType;
    public static final String NOT_DONE = "[\u00D7]";
    public static final String DONE = "[\u221A]";

    public Task(String taskName){
        setTaskName(taskName);
        setTaskStatus(NOT_DONE);
        taskNum++;
    }

    public static void setTaskNum(int taskNum){
        Task.taskNum = taskNum;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public void setTaskStatus(String taskStatus){
        this.taskStatus = taskStatus;
    }

    public String getTaskName(){
        return this.taskName;
    }

    public String getTaskStatus(){
        return this.taskStatus;
    }

    public String getTaskType(){
        return this.taskType;
    }

    public static int getTaskNum(){
        return taskNum;
    }

    public String showTaskInfo(){
        String info = this.getTaskType()+this.getTaskStatus()+" "+this.getTaskName();
        return info;
    }
}
