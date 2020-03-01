package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);

        this.taskDescription = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
