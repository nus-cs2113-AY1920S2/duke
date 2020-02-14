package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.type, super.toString());
    }
    
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s", type, isDone ? 1 : 0, description);
    }
}
