package duke.command;

public class ClearCommand extends Command {

    public final static String USAGE = "clc";

    public ClearCommand () {
        super(null, null);
    }

    /**
     * Prints new lines to make it look like the command window was cleared
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
