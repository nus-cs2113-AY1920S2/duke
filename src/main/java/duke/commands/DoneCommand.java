package duke.commands;

import duke.exceptions.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;
import duke.tasks.Task;

import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Defines the constructor.
     * Specifies the task number in the list of the done task.
     *
     * @param taskNumber Number of the task in the task list.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes command "done".
     * Marks the task with the task number as done.
     * Updates txt file whenever the task list changes.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     * @throws InvalidTaskNumberException If the task number is negative or outside the existing index range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException{
        if (taskNumber > tasks.getListSize() || taskNumber <= 0) {
            throw new InvalidTaskNumberException();
        }
        
        Task task = tasks.getTask(taskNumber);
        task.markAsDone();
        ui.displayDoneCommandMessage(task);
        
        // update the txt file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
