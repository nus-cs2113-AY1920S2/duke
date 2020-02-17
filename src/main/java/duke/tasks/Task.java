package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String saveFormat() {
        return String.valueOf(isDone) + "//" + this.description;
    }

    @Override
    public String toString() {
        return '[' + this.getStatusIcon() + "] " + this.description;
    }
}
