package hiroshi.tasklist;

import hiroshi.tasks.Task;

import java.util.ArrayList;

public class printTaskList extends TaskList{
    public printTaskList(){
        super();
    }

    /**
     * Print all available tasks.
     *
     * @param taskList List that stores the tasks mentioned until now.
     */
    public static void execute(ArrayList<Task> taskList) {
        int taskCounter = 1;
        for (Task task : taskList) {
            String description = task.getDescription();
            String statusIcon = task.getStatusIcon();
            String typeIcon = task.getTypeIcon();
            System.out.println(taskCounter + ". " + typeIcon + " [" + statusIcon + "] " + description);
            taskCounter += 1;
        }
    }
}
