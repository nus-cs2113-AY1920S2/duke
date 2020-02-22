package duke;

import duke.task.Task;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public Task getOneTask(int taskIndex){
        return taskList.get(taskIndex);
    }

    public int getLenOfList(){
        return this.taskList.size();
    }

    public void append(Task task){
        this.taskList.add(task);
    }

    public void remove(int taskIndex){
        this.taskList.remove(taskIndex-1);
        Task.setTaskNum(Task.getTaskNum()-1);
    }

    public void printTaskList(){
        int index = 0;
        for(Task task:taskList){
            index++;
            String prefix = "    "+index+".";
            System.out.println(prefix+task.showTaskInfo());
        }
    }
}
