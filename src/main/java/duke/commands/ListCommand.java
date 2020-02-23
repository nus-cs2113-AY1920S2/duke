package duke.commands;

import duke.format.Printer;

import static duke.format.Printer.LIST_MESSAGE;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute() {
        return new CommandResult(LIST_MESSAGE, true);
    }
}
