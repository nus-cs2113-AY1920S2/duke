package duke.command;

import duke.ui.Output;

public class InvalidCommand extends Command {

    public InvalidCommand (Output printer) {
        super(null, printer);
    }

    @Override
    public void execute () {
        printer.printInvalidCommand();
    }
}
