package Duke;

public abstract class Task {
    public static final String TICK = "✓";
    public static final String CROSS = "✘";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? TICK : CROSS); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        return null;
    }

    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" + getStatusIcon() + "] " + getDescription());
        System.out.println("\nYou've got " + taskCounter + " task(s) in the list!\n");
    }

    public abstract void printListDetails(int count);

}
