package duke.command;

public class ExitCommand extends Command{
    public ExitCommand() {

    }

    @Override
    public Boolean isExit() {
        return true;
    }
}
