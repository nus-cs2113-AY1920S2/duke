package chatty.command;

/**
 * Task command used in the application.
 */
public abstract class TaskCommand extends Command {

    private String description;

    /**
     * Constructor for task command.
     * @param description Description of the task in the command.
     */
    public TaskCommand(String description) {
        super();
        this.description = description.trim();
    }

    /**
     * Gets description of the task in the command.
     * @return Description of the task in the command.
     */
    public String getDescription() {
        return description;
    }
}
