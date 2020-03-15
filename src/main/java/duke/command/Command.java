package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Abstract Command class, a superclass to provide some template methods
 * for the subclasses to override
 */
public abstract class Command {

    /**
     * Method to set the termination of the program to false in default
     *
     * @return false which is not terminating
     */
    public boolean isExit() {
        return false;
    }

    /** An abstract method to execute a command, given a task and a storage to save any changes.
     *
     * @param storage the storage to save any changes.
     * @param ui the user interface to inform them
     * @param tasks the user's TaskList.
     * @throws IOException in case something wrong with input
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}