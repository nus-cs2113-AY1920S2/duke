public class Task {

    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }

    public String getStatus() {
        if (isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
