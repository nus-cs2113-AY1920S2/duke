public class Deadline extends Task {
    private String latestDate;

    public Deadline(String userInput) {
        super(userInput.substring(0, userInput.indexOf(" /")));
        this.latestDate = userInput.substring(userInput.indexOf("/") + 4);
    }

    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", isDone() ? '✓' : '✗', taskName, latestDate);
    }
}
