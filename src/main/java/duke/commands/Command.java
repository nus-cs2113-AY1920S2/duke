package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;

/**
 * Objects for all commands.
 */
public abstract class Command {
    /** Represents if this command means "Exit" or not. */
    protected boolean isExit = false;

    /** Deliminator between the task content and the date. */
    protected String marker; // to be assigned in subclasses

    /**
     * Returns if this command means "Exit" or not.
     * 
     * @return True if the command means "Exit", False otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns the marker for the command: /by for deadline, /at for event, /on for check.
     * 
     * @return Marker of the command.
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * Executes the command according to different types.
     * 
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     * @throws ChatboxException Different types of ChatboxException specified in each specific implementation.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException;
}
