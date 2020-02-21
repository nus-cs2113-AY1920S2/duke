package chatty.command;

public class DeleteCommand extends Command {

    private int idx;

    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }
}
