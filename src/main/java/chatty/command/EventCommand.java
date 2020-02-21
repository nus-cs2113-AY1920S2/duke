package chatty.command;

public class EventCommand extends TaskCommand {

    private String eventPeriod;

    public EventCommand(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod.trim();
    }

    public String getEventPeriod() {
        return eventPeriod;
    }
}
