package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;

public class IncorrectCommand extends Command {
    public final String feedbackToUser = "Unable to execute \"%1$s\". " +
            "Please try again with valid command.";
    public final String cmd;

    public IncorrectCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        return new CommandResult(String.format(feedbackToUser, cmd));
    }
}
