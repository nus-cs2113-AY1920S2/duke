package duke.command;

/**
 * A class representing a command to clear the terminal.
 */
public class ClearCommand extends Command {

    public final static String USAGE = "clc";

    public ClearCommand () {
        super(null);
    }

    /**
     * Clears the terminal by printing new lines.
     */
    @Override
    public CommandResult execute () {
        int newLinesToPrint = 100;

        for (int i = 0; i < newLinesToPrint; i++) {
            System.out.println();
        }

        return new CommandResult();
    }

}
