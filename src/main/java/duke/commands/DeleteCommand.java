package duke.commands;

import duke.exceptions.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;

import java.io.IOException;

import static duke.utils.Constants.DELETE_COMMAND;

public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Defines the constructor.
     * Specifies the task number in the task list of the unwanted task.
     * 
     * @param taskNumber Number of the task in the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes command "delete".
     * Removes the unwanted task out of the existing task list.
     * Updates txt file whenever the task list changes.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     * @throws InvalidTaskNumberException If the task number is negative or outside the existing index range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException {
        if (taskNumber > tasks.getListSize() || taskNumber <= 0) {
            throw new InvalidTaskNumberException();
        }
        
        ui.displayDeleteTaskMessage(tasks, taskNumber);
        tasks.deleteTask(taskNumber);

        // update the txt file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
