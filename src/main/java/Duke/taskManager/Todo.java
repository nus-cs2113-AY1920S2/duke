package duke.taskManager;

public class Todo extends Task {
    private String description;

    /**
     * Public constructor for Todo Task
     *
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return a string representation of the task
     *
     * @return [taskStatus] followed by the description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

