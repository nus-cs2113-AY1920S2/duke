package duke.task;

/**
 * Represents the type 'todo', and extension of the Task class. A todo object corresponds
 * to a particular task that the user still has to complete, e.g 'eat lunch'
 */
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
