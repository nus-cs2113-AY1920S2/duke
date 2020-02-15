package duke.command;

import duke.ui.Output;

public class ExitCommand extends Command {

    public ExitCommand (Output printer) {
        super(null,printer);
    }

    @Override
    public void execute() {
        printer.displayFarewell();
        System.exit(0);
    }
}
