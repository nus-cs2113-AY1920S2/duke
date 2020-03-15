package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.WhitespaceExceptions;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

import static duke.constants.Constants.INDEX_OUT_OF_BOUND;
import static duke.constants.Constants.NOT_INTEGER;

/**
 * A command object to mark tasks as done
 */
public class DoneCommand implements Command {
    private String line;

    public DoneCommand(String restOfInput) {
        this.line = restOfInput;
    }

    /**
     * Done command to mark task as done in the array list and tasklist.txt
     *
     * @param function String containing "done"
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean true to main function
     * @throws WhitespaceExceptions
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks)
            throws WhitespaceExceptions {
        try {
            String l = line.replace(" ", "");

            int taskNumber = Integer.parseInt(l) - 1;
            if (taskNumber >= tasks.size() || taskNumber < 0) {
                throw new DukeException(INDEX_OUT_OF_BOUND);
            }
            tasks.get(taskNumber).markAsDone(tasks.get(taskNumber));
            String str = tasks.get(taskNumber).toString().substring(6);
            storage.appendToFile(str);
            ui.showDoneTask(tasks, taskNumber);
        } catch (NumberFormatException e) {
            ui.printToUser(NOT_INTEGER);
        } catch (DukeException e) {
            e.printDescr();
        }
        return true;
    }
}
