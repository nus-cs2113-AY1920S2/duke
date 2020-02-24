package duke.commands;

import static duke.ui.Messages.LIST_MESSAGE;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = "list";

    @Override
    public CommandResult execute() {
        return new CommandResult(LIST_MESSAGE, true);
    }
}
