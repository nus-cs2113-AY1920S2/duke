package duke.task;

public class Task {

    protected String description;

    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        isCompleted = false;
    }


    public String getStatus() {
        if (isCompleted) {
            return "Complete";
        } else {
            return "Not complete";
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
