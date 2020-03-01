package duke.task;

public class Todo extends Task {

    //constructor for the Todo class
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
