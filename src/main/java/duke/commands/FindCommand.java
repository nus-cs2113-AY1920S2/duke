package duke.commands;

import duke.util.UI;
import duke.taskmanager.Task;

import java.util.List;

public class FindCommand extends Command {
    private static UI ui = new UI();
    private static boolean isFound = false;
    public FindCommand() {
    }

    /**
     * Get the user input for keyword to search in the list.
     * Print the tasks in the task list that contains the
     * specific keyword entered by the user.
     * @param list the current task list
     */
    public void execute(List<Task> list) {
        System.out.println("    Please enter the task that you want to find:");
        String keyword = ui.getStringInput();
        int index = 0;
        for (Task task : list) {
            if (task.getTask().contains(keyword)) {
                System.out.println("    "+ index + ": " +
                        task);
                isFound = true;
            }
            index ++;
        }
        keywordNotFound(keyword);
    }

    private void keywordNotFound(String keyword) {
        if (!isFound) {
            System.out.println("    Task " + keyword + " is not in the list.");
        }
    }
}