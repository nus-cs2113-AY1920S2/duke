package chatty.command;

/**
 * Delete command used in the application.
 */
public class DeleteCommand extends Command {

    private int idx;

    /**
     * Constructor for delete command.
     * @param idx Index in the delete command.
     */
    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Gets index from the delete command.
     * @return The index in the delete command.
     */
    public int getIdx() {
        return idx;
    }
}
