package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

import static duke.constants.Constants.MATCHING_TASK_MESSAGE;
import static duke.constants.Constants.NO_MATCH_MESSAGE;
import static duke.constants.Constants.LINE_DIVIDER;

/**
 * Find Command object to find tasks in the list
 */
public class FindCommand implements Command {
    private String line;

    public FindCommand(String restOfInput) {
        this.line = restOfInput;
    }

    /**
     * A command object to find task in the array list based on descriptions
     *
     * @param function String containing "find"
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean true to main function
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        boolean found = false;
        String[] keywords = line.trim().split("\\s+");
        ui.printToUser(MATCHING_TASK_MESSAGE);
        for (String word : keywords) {
            for (int i = 0; i < tasks.size(); i++) {
                String taskDescription = tasks.get(i).getDescription();
                if (taskDescription.contains(word)) {
                    int index = i + 1;
                    ui.printToUser("    " + index + "." + tasks.get(i));
                    found = true;
                }
            }
        }
        ui.printToUser("\n" + LINE_DIVIDER);
        if (!found) {
            ui.printToUser(NO_MATCH_MESSAGE + LINE_DIVIDER);
        }
        return true;
    }
}
