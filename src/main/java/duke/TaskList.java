package duke;

import duke.task.Task;
import java.util.ArrayList;


public class TaskList {
    protected static ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public Task getOneTask(int taskIndex){
        return taskList.get(taskIndex);
    }

    public static int getLenOfList(){
        return taskList.size();
    }

    public void append(Task task){
        taskList.add(task);
    }

    public void remove(int taskIndex){
        taskList.remove(taskIndex-1);
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

    public void showAllRelatedTasks(String targetWords){
        int index = 0;

        for(Task task:taskList){
            if(!task.showTaskInfo().contains(targetWords)) continue;
            index++;
            String prefix = "    "+index+".";
            if(index==1) System.out.println("    Here are the matching tasks in your list:");
            System.out.println(prefix+task.showTaskInfo());
        }

        if(index==0) System.out.println("    No matching tasks found!");
    }
}
