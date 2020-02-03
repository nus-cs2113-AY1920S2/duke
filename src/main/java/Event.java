public class Event extends Task {
    private String eventDetails;

    public Event(String userInput) {
        super(userInput.substring(0, userInput.indexOf(" /")));
        this.eventDetails = userInput.substring(userInput.indexOf("/") + 4);
    }

    @Override
    public String toString() {
        return String.format("[E][%c] %s (at: %s)", isDone() ? '✓' : '✗', taskName, eventDetails);
    }
}
