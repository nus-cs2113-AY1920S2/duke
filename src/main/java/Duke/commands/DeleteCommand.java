package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.WhitespaceExceptions;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

import static duke.constants.Constants.NOT_INTEGER;
import static duke.constants.Constants.INDEX_OUT_OF_BOUND;

/**
 * A command object to delete tasks
 */
public class DeleteCommand implements Command {
    private String line;

    public DeleteCommand(String restOfInput) {
        this.line = restOfInput;
    }

    /**
     * Delete command to delete task from the array list and tasklist.txt
     *
     * @param function String containing "delete"
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean true to main function
     * @throws WhitespaceExceptions
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        int taskNumber;
        String str;
        try {
            taskNumber = Integer.parseInt(line.replaceAll(" ", "")) - 1;
            if (taskNumber >= tasks.size() || taskNumber < 0) {
                throw new DukeException(INDEX_OUT_OF_BOUND);
            }
            ui.showDeletedTasks(tasks, taskNumber);
            str = tasks.get(taskNumber).toString().substring(6);
            storage.deleteToFile(str);
            taskList.deleteTask(tasks, taskNumber);
        } catch (NumberFormatException e) {
            ui.printToUser(NOT_INTEGER);
        } catch (DukeException e) {
            e.printDescr();
        }
        return true;
    }
}
