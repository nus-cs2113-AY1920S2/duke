package chatty.command;

/**
 * Done command used in the application.
 */
public class DoneCommand extends Command {

    private int idx;

    /**
     * Constructor for done command.
     * @param idx Index in the done command.
     */
    public DoneCommand(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Gets index from the done command.
     * @return The index in the done command.
     */
    public int getIdx() {
        return idx;
    }
}
