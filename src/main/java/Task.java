public class Task {
    private String description;
    private boolean isCompleted;

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

    public void markAsDone() {
        isCompleted = true;
    }
}
