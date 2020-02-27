package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;
import duke.tasks.Task;

import java.io.IOException;

import static duke.utils.Constants.DONE_COMMAND;

public class DoneCommand extends Command {
    private int taskNumber;
    
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.command = DONE_COMMAND;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException{
        if (taskNumber > tasks.getListSize() || taskNumber <= 0) {
            throw new ChatboxException();
        }
        Task task = tasks.getTask(taskNumber);
        task.markAsDone();
        ui.displayDoneCommandMessage(task);
        
        // update the file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
