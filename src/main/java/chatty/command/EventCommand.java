package chatty.command;

import java.time.LocalDate;

/**
 * Event command used in the application.
 */
public class EventCommand extends TaskCommand {

    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for event command.
     * @param description Description of the event task in the command.
     * @param eventPeriod Event period of the event task in the command.
     */
    public EventCommand(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets event start time in the event command.
     * @return Event start time in the event command.
     */
    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Gets event end time in the event command.
     * @return Event end time in the event command.
     */
    public LocalDate getEndTime() {
        return endTime;
    }
}
