package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Deals with command that can find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    /** User input keyword.*/
    private static String findString;
    /** Stores the task index that matches.*/
    private static ArrayList<Integer> findCount;

    public FindCommand(String findString) {
        this.findString = findString;
        findCount = new ArrayList<>();
    }

    /**
     * Finds tasks by searching for the keyword.
     *
     * @param tasks Stores all tasks.
     * @param ui Deals with user interface.
     * @param storage Deals with back up file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getATask(i).getDescription().contains(findString)) {
                findCount.add(i);
            }
        }
        ui.showFindTask(tasks,findCount);
    }
}
