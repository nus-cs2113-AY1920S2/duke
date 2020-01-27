import java.util.Arrays;

public class TaskList {
    private Task[] taskList;
    private int lenOfList;

    public TaskList(){
        this.lenOfList = 0;
        this.taskList = new Task[0];
    }

    public Task getOneTask(int taskIndex){
        return taskList[taskIndex];
    }

    public int getLenOfList(){
        return this.lenOfList;
    }

    public void append(Task task){
        this.taskList = Arrays.copyOf(taskList,lenOfList+1);
        this.lenOfList += 1;
        taskList[lenOfList-1] = task;
    }

    public void printTaskList(){
        int index = 0;
        for(Task task:taskList){
            index++;
            System.out.println("    "+index+"."+task.getTaskStatus()+task.getTaskName());
        }
    }
}
