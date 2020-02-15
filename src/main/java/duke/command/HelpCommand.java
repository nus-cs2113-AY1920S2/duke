package duke.command;

import duke.ui.Output;

public class HelpCommand extends Command {


    public HelpCommand (Output printer) {
        super(null, printer);
    }

    /**
     * Prints the descriptions on how to use each one of the supported commands
     */
    public void execute() {
        printer.printHelp();
    }

}
