package duke.taskList;

import duke.taskManager.Task;
import duke.taskManager.Todo;

import java.util.ArrayList;

public class TaskList {
   // private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(String line, ArrayList<Task> tasks){
        tasks.add(new Todo(line));
    }

    public void deleteTask(){

    }

    public void printAddedTask(Task t, ArrayList<Task> tasks) {
        System.out.println("\n____________________________________________________________");
        System.out.println("    New task added:");
        System.out.println("    " + t);
        System.out.println("    Total number of tasks in the list:  " + tasks.size());
        System.out.println("____________________________________________________________");
    }
}
