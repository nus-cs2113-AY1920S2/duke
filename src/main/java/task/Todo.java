package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToData() {
        int isDoneAsInt = this.isDone ? 1 : 0;
        return String.format("T|" + isDoneAsInt + "|" + this.description);
    }
}