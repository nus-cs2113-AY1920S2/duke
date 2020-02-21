package duke.commands;

import duke.exception.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public class Command {

    /** Flag that will be set to true if "bye" command is encountered */
    public boolean isExit = false;

    /**
     * Throws an exception if invoked because this function can only be invoked for child classes.
     *
     * @param tasks <code>TaskList</code> object that the command will execute on.
     * @param textUi <code>Ui</code> object that is being used to display output to the user.
     * @param storage <code>Storage</code> object that is able to access tasks saved in memory.
     * @return <code>CommandResult</code>.
     * @throws InvalidCommandException If this function is invoked for this class.
     */
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException();
    }

}
