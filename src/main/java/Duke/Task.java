package Duke;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public void markAsDone(Task description) {
        this.isDone = true;
    }

    public void importDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + getDescription();
    }

}
