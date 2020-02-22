package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    private String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "✓";
        } else {
            return "✗"; //return tick or X symbols
        }
    }

    public String getType() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public int getIsDone() {
        if (isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getTime() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
