package src.main.java.duke.task;

public class Todo extends Task {
    private final String TODO = "T";

    public Todo(String description) {
        super(description);
        this.taskType = TODO;
    }

    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }
}
