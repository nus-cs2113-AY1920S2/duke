package duke.command;

import duke.ui.Ui;

/**
 * A class representing the command to exit the program.
 */
public class ExitCommand extends Command {

    public final static String USAGE = "bye";
    private Ui printer;

    /**
     * Initializes needed objects for the command to be executed
     *
     * @param printer UI to display farewell message.
     */
    public ExitCommand (Ui printer) {
        super(null);
        this.printer = printer;
    }

    @Override
    public CommandResult execute() {
        printer.displayFarewell();
        System.exit(0);

        return null;
    }
}
