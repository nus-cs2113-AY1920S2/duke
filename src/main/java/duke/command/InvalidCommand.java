package duke.command;

import duke.ui.Ui;
import duke.utility.Messages;

public class InvalidCommand extends Command {

    public InvalidCommand (Ui printer) {
        super(null, printer);
    }

    @Override
    public CommandResult execute () {
        return new CommandResult(Messages.INVALID_COMMAND);
    }
}
