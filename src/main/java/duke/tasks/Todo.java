package duke.tasks;

/**
 * Class for Todo.
 */
public class Todo extends Task {

    /**
     * Constructor for abstract Todo class.
     * @param description String for description of task.
     */
    public Todo(String description) {
        super(description);
        this.taskDescription = "todo";
    }

    /**
     * To display todo task message to user.
     * @return String consisting of todo task and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
