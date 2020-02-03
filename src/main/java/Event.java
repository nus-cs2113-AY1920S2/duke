public class Event extends Task {
    protected String startEndDateTime;

    public Event(String description, String startEndDateTime) {
        super(description);
        this.startEndDateTime = startEndDateTime;
    }

    public static boolean validateUserInput(String userInput) {
        if (!userInput.contains(" /at ")) {
            return false;
        } else if (userInput.indexOf(" /at ") + 5 > userInput.length() - 1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startEndDateTime + ")";
    }
}
