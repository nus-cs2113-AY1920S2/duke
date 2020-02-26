package Command;

public class FailedCommand extends Command {
    private String errorMessage;

    public FailedCommand(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute() {

    }

    //print error message?
}
