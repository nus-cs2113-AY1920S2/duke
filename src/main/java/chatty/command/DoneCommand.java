package chatty.command;

public class DoneCommand extends Command {

    private int idx;

    public DoneCommand(int idx) {
        super();
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }
}
