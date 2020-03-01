package duke;

/**
 * ExitCommand extended from the Command class to deal with the
 * termination of the program
 */
public class ExitCommand extends Command{
    /**
     * Override method isExit in the Command class
     * Method to set exit to true in order to terminate the program
     * @return true termination of the program
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /** Override abstract method of Command Class.
     * execute an ExitCommand in TaskList class
     *
     * @param tasks the user's TaskList.
     * @param ui the user interface to inform them
     * @param storage the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
