package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description.trim());
    }

    @Override
    public String saveFormat() {
        return "t//" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
