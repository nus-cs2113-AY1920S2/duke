package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.description;
    }

    public boolean containsString(String userInput) {
        return this.description.toLowerCase().contains(userInput.toLowerCase());
    }
}
