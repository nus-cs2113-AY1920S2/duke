package duke.command;

import duke.ui.Ui;

public class ExitCommand extends Command {

    public final static String USAGE = "bye";

    public ExitCommand (Ui printer) {
        super(null,printer);
    }

    @Override
    public CommandResult execute() {
        printer.displayFarewell();
        System.exit(0);

        return null;
    }
}
