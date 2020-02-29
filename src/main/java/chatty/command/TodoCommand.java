package chatty.command;

/**
 * Todo command used in the application.
 */
public class TodoCommand extends TaskCommand {

    /**
     * Constructor for todo command.
     * @param description Description of the todo task in the command.
     */
    public TodoCommand(String description) {
        super(description);
    }
}
