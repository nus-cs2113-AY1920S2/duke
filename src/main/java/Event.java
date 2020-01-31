public class Event extends Task {
    protected String eventDate;
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
        this.itemType = 'E';
    }

    @Override
    public String printObject() {
        return ("[" + itemType + "][" + getStatusIcon() +"] "+ description + " (at: " + eventDate + ")");
    }
}
