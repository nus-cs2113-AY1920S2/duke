/**
 * Extends from Task. Stores the information needed for Event.
 */

public class Event extends Task {

    /**
     * The details of the event as stated by the user.
     */

    private String eventDetails;

    /**
     * Constructor for Event.
     * @param userInput the command that the user typed in.
     */
    public Event(String userInput) {
        super(userInput.substring(0, userInput.indexOf(" /")));
        this.eventDetails = userInput.substring(userInput.indexOf("/") + 4);
    }

    @Override
    public String toString() {
        return String.format("[E][%c] %s (at: %s)", isDone() ? '✓' : '✗', taskName, eventDetails);
    }
}
