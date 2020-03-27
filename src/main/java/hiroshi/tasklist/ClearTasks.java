package hiroshi.tasklist;

import hiroshi.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a class that clears all tasks from the taskList.
 */
public class ClearTasks extends TaskList {
    public ClearTasks(){
        super();
    }

    /**
     * Method used to clear tasks from taskList.
     *
     * @param taskList List that stores the tasks mentioned until now.
     */
    public static void execute(ArrayList<Task> taskList){
        taskList.clear();
    }
}