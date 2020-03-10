/**
 * Extends from Task. Stores the information needed for Deadline.
 */

public class Deadline extends Task {

    /**
     * The latest date the task must be completed.
     */

    private String latestDate;

    /**
     * Constructor for Deadline.
     * @param userInput the command that the user typed in.
     */

    public Deadline(String userInput) {
        super(userInput.substring(0, userInput.indexOf(" /")));
        this.latestDate = userInput.substring(userInput.indexOf("/") + 4);
    }

    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", isDone() ? '✓' : '✗', taskName, latestDate);
    }
}
