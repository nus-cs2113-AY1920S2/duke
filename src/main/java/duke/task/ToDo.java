package duke.task;

public class ToDo extends Task{
    private static int toDoNum = 0;

    public ToDo(String taskName){
        super(taskName);
        toDoNum++;
        this.taskType = "[T]";
    }

    public ToDo(String taskName,String taskStatus){
        this(taskName);
        this.setTaskStatus(taskStatus);
    }

    public static int getToDoNum(){
        return toDoNum;
    }

}
