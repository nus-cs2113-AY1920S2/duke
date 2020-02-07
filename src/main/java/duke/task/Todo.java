package duke.task;

public class Todo {
    private String description;
    private boolean isDone;
    private final static char taskType = 'T';

    public Todo(String description) {
        this.isDone = false;
        this.description = description;
    }

    private String isDone() {
        return this.isDone ? "[✓] " : "[✗] ";
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("  " + "[✓] " + this.description);
    }

    public char getTaskType() {
        return taskType;
    }

    public String toString() {
        return String.format("[%c]", getTaskType()) + isDone() + this.description;
    }
}
