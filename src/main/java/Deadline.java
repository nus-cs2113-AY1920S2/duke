public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(String userInput) throws BadDeadlineFormatException {
        if (!userInput.contains(" /by ")) {
            throw new BadDeadlineFormatException("input does not contain \" /by \"");
        } else if (userInput.indexOf(" ") + 1 > userInput.indexOf(" /by ")) {
            throw new BadDeadlineFormatException("input does not contain a description");
        } else if (userInput.indexOf(" /by ") + 5 > userInput.length() - 1) {
            throw new BadDeadlineFormatException("input does not contain a due date/time");
        }
        this.description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(" /by "));
        this.dueDateTime = userInput.substring(userInput.indexOf(" /by ") + 5);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }
}
