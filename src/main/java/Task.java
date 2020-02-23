public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        String description = this.description;
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides the default toString so that Task gets printed in a specific format
     *
     * @return the formatted String to print
     */
    public String toString() {
        String symbol = getStatusIcon();
        String toPrint = String.format("[%s] %s", symbol, this.description );
        return toPrint;
    }
}
