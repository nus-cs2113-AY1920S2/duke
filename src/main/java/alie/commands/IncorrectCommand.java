package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;

/**
 * Command that deals with invalid commands given by user.
 */
public class IncorrectCommand extends Command {
    public final String feedbackToUser = "Unable to execute \"%1$s\". " +
            "Please try again with valid command.";
    public final String cmd;

    /**
     * Default constructor to initialise the cmd given by user.
     * @param cmd Invalid input provided by the user.
     */
    public IncorrectCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        return new CommandResult(String.format(feedbackToUser, cmd));
    }
}
