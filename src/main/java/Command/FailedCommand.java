package Command;

/**
 * Assist auxiliary parser methods if failed validation
 */
public class FailedCommand extends Command {
    private String errorMessage;

    /**
     * Saves a custom message when command is returned
     *
     * @param errorMessage
     */
    public FailedCommand(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute() {
        System.out.println(errorMessage);
    }

}
