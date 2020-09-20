package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;

/**
 * Command to signal to exit the app.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "bye";
    public static final String EXIT_PROG_ACK = "Exiting A.L.I.E...";

    /**
     * Checks if user wants to exit the app by checking given command.
     * @param command The Command to be checked.
     * @return True if it is an object of type ExitCommand.
     * False if it is not an object of type ExitCommand.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        return new CommandResult(EXIT_PROG_ACK);
    }
}
