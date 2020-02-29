package chatty.command;

import java.time.LocalDate;

/**
 * Date command used in the application.
 */
public class DateCommand extends Command {

    private LocalDate dateTime;

    /**
     * Constructor for date command.
     * @param dateTime The datetime in the date command.
     */
    public DateCommand(LocalDate dateTime) {
        super();
        this.dateTime = dateTime;
    }

    /**
     * Gets datetime in the date command.
     * @return Datetime in the date command.
     */
    public LocalDate getDateTime() {
        return dateTime;
    }
}
