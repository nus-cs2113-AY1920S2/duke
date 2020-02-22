package chatty.command;

/**
 * Event command used in the application.
 */
public class EventCommand extends TaskCommand {

    private String eventPeriod;

    /**
     * Constructor for event command.
     * @param description Description of the event task in the command.
     * @param eventPeriod Event period of the event task in the command.
     */
    public EventCommand(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod.trim();
    }

    /**
     * Gets event period in the event command.
     * @return Event period in the event command.
     */
    public String getEventPeriod() {
        return eventPeriod;
    }
}
