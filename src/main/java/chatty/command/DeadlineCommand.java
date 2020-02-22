package chatty.command;

/**
 * Deadline command used in the application.
 */
public class DeadlineCommand extends TaskCommand {

    private String dateTime;

    /**
     * Constructor for deadline command.
     * @param description Description of the deadline task in the command.
     * @param dateTime Datetime of the deadline task in the command.
     */
    public DeadlineCommand(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime.trim();
    }

    /**
     * Gets datetime in the deadline command.
     * @return Datetime in the deadline command.
     */
    public String getDateTime() {
        return dateTime;
    }
}
