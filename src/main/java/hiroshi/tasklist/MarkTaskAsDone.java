package hiroshi.tasklist;

import hiroshi.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a class that marks a task as done.
 */
public class MarkTaskAsDone extends TaskList {

    public MarkTaskAsDone(){
            super();
        }

    /**
     * Marks a task as done.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        String[] words = ogString.split(" ");
        taskList.get(Integer.parseInt(words[1]) - 1).markIt();
        String statusIcon = taskList.get(Integer.parseInt(words[1]) - 1).getStatusIcon();
        String typeIcon = taskList.get(Integer.parseInt(words[1]) - 1).getTypeIcon();
        String description = taskList.get(Integer.parseInt(words[1]) - 1).getDescription();
        System.out.println((Integer.parseInt(words[1])) + ". " + typeIcon + "[" + statusIcon + "]" + " " + description);
        System.out.println("Done! We have checked " + words[1] + "!");
    }
}