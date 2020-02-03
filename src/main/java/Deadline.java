public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    public static boolean validateUserInput(String userInput) {
        if (!userInput.contains(" /by ")) {
            return false;
        } else if (userInput.indexOf(" /by ") + 5 > userInput.length() - 1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }
}
