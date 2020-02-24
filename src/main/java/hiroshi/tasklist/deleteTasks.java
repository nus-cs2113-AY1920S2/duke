package hiroshi.tasklist;

import hiroshi.tasks.Task;

import java.util.ArrayList;

/** Represents command that is used to call a method to delete tasks to taskList. */
public class deleteTasks extends TaskList {

    public deleteTasks(){
        super();
    }

    /**
     * Deletes the task at index taskNumber from the tasklist
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        String[] words = ogString.split(" ");
        int taskNumber = Integer.parseInt(words[1].trim()) - 1;
        Task task = taskList.get(taskNumber);
        String description = task.getDescription();
        String statusIcon = task.getStatusIcon();
        String typeIcon = task.getTypeIcon();
        System.out.println("Cool, we will remove the following task:");
        System.out.println(typeIcon + " [" + statusIcon + "] " + description);
        taskList.remove(taskNumber);
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " items in your list");
    }
}
