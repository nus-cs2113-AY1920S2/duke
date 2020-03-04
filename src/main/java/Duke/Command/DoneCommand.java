package Duke.Command;

import Duke.UI.Ui;
import Duke.UI.Messages;
import Duke.Tasks.Task;
import Duke.TaskList;
import Duke.Storage;
import Duke.DukeException;

/**
 * Command that marks tasks as done.
 */
public class DoneCommand extends Command {

    protected String command;
    protected String fullCommand;
    protected String errorMessage;

    /**
     * Constructor of DoneCommand.
     *
     * @param command "done" command specified by user.
     * @param fullCommand entire input from user.
     */
    public DoneCommand (String command, String fullCommand) {
        this.command = command;
        this.fullCommand = fullCommand;
        this.errorMessage = String.format(Messages.MESSAGE_INVALID_DESCRIPTION,
                command, Messages.DONE_DELETE_ERROR_MESSAGE);
    }

    /**
     * Determines the task specified by number in the list and marks that task as done.
     *
     * @return The task being marked as done.
     * @throws DukeException if the user did not specify a number in the list.
     */
    public Task doneTask() throws DukeException {
        int number = Integer.parseInt(fullCommand.substring(5));
        if (number > TaskList.getSize()) {
            throw new DukeException();
        }
        Task t = TaskList.fetchTask(number-1);
        t.markAsDone();
        return t;
    }

    /**
     * Executes the command.
     *
     * @param tasks object of TaskList class containing list of tasks in the program.
     * @param ui object of Ui class handling printing output to the user.
     * @param storage object of Storage class for saving program to file.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = doneTask();
            ui.out.println(String.format(Messages.MESSAGE_DONE_SUCCESS, t));
        } catch (NumberFormatException e) {
            ui.out.println(errorMessage);
        } catch (IndexOutOfBoundsException e) {
            ui.out.println(errorMessage);
        } catch (DukeException e) {
            ui.out.println(errorMessage);
        }
    }

    /**
     * Indicates program not ready to exit.
     *
     * @return isExit() is false and program should continue running.
     */
    @Override
    public boolean isExit(){
        return false;
    };

}