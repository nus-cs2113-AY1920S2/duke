public class Event extends Task {
    protected String startEndDateTime;

    public Event(String userInput) throws BadEventFormatException {
        if (!userInput.contains(" /at ")) {
            throw new BadEventFormatException("input does not contain \" /at \"");
        } else if (userInput.indexOf(" ") + 1 > userInput.indexOf(" /at ")) {
            throw new BadEventFormatException("input does not contain a description");
        } else if (userInput.indexOf(" /at ") + 5 > userInput.length() - 1) {
            throw new BadEventFormatException("input does not contain a date/time");
        }
        this.description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" /at "));
        this.startEndDateTime = userInput.substring(userInput.indexOf(" /at ") + 5);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startEndDateTime + ")";
    }
}
