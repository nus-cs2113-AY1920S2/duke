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
    
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.command = DELETE_COMMAND;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException {
        if (taskNumber > tasks.getListSize() || taskNumber <= 0) {
            throw new InvalidTaskNumberException();
        }
        
        ui.displayDeleteTaskMessage(tasks, taskNumber);
        tasks.deleteTask(taskNumber);

        // update the file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
