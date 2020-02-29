package duke.commands;

import duke.exceptions.IllegalDoneTaskException;
import duke.util.UI;
import duke.taskmanager.Tasks;

import java.util.List;

public class FindCommand extends Command {
    UI ui;
    private static boolean isFound = false;
    public FindCommand(UI ui) {
        this.ui = ui;
    }

    /**
     * Get the user input for keyword to search in the list.
     * Print the tasks in the task list that contains the
     * specific keyword entered by the user.
     * @param list the current task list
     */
    public void execute(List<Tasks> list) {
        System.out.println("    Please enter the task that you want to find:");
        String keyword = ui.getStringInput();
        int index = 0;
        for (Tasks task : list) {
            if (task.getTask().contains(keyword)) {
                System.out.println("    "+ index + ": " +
                        task);
                isFound = true;
            }
            index ++;
        }
        if (!isFound) {
            System.out.println("    Task " + keyword + " is not in the list.");
        }
    }
}