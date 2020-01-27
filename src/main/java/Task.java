public class Task {
    private static int taskNum = 0;
    private String taskStatus;
    private String taskName;

    public Task(){
        this("");
    }

    public Task(String taskName){
        setTaskName(taskName);
        if(!taskName.equals(""))
            setTaskNum(Task.taskNum+1);
        setTaskStatus("[\u2717]");
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

    public static int getTaskNum(){
        return taskNum;
    }
}
