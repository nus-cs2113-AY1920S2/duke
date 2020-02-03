public class Task {
    protected String action;
    protected boolean isDone;

    public Task(String action) {
        this.action = action;
        this.isDone = false;
    }

    public String checkIfDone() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public boolean markAsDone() {
        return isDone = true;
    }

    @Override
    public String toString() {
        return "[" + checkIfDone() + "] " + action;
    }
}
