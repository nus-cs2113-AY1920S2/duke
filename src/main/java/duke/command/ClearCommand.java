package duke.command;

public class ClearCommand extends Command {


    public ClearCommand () {
        super(null, null);
    }

    /**
     * Prints new lines to make it look like the command window was cleared
     */
    @Override
    public void execute () {
        int newLinesToPrint = 100;

        for (int i = 0; i < newLinesToPrint; i++) {
            System.out.println();
        }
    }

}
