package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;

public class markTaskAsDone extends TaskList {
    public markTaskAsDone(){
            super();
        }
    /**
     * Returns a boolean that marks a task as done if it exists
     * Marks a task as done
     *
     * @param taskList TaskList containg all tasks
     * @param ogString Inputted command by the user that is fed as a string
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
