package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "bye";
    public static final String EXIT_PROG_ACK = "Exiting A.L.I.E...";

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        return new CommandResult(EXIT_PROG_ACK);
    }
}
