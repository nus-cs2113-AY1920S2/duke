package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadTaskChoiceFormatException;

/**
 * Abstract class that represents a command, parsed from the user's input. The command stores the tokens from the
 * user's input and keeps a reference to a <code>TaskList</code>
 */
public abstract class Command {
    protected TaskList taskList;
    protected boolean isPersistentCommand = true;

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * @return whether or not to save to file after this command is executed
     */
    public boolean getIsPersistentCommand() {
        return isPersistentCommand;
    }

    /**
     * Abstract method that is called to execute a command instance.
     * @throws BadTaskChoiceFormatException if a <code>TaskSelectionCommand</code> gets a bad task number
     */
    public abstract void execute() throws BadTaskChoiceFormatException;
}
