package chatty.command;

import java.time.LocalDate;

/**
 * Deadline command used in the application.
 */
public class DeadlineCommand extends TaskCommand {

    private LocalDate dateTime;

    /**
     * Constructor for deadline command.
     * @param description Description of the deadline task in the command.
     * @param dateTime Datetime of the deadline task in the command.
     */
    public DeadlineCommand(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Gets datetime in the deadline command.
     * @return Datetime in the deadline command.
     */
    public LocalDate getDateTime() {
        return dateTime;
    }
}
