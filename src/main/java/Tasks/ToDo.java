package Tasks;

/**
 * Represents a ToDo object for tasks
 * A ToDo object corresponds to a description, and boolean isDone to check if Tasks.Event task has been completed
 */
public class ToDo extends Task {

    protected String description;
    protected boolean isDone;

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description , isDone);
    }

    /**
     * Returns String format for ToDo tasks to be printed on CLI
     * @return String format of ToDo tasks
     */
    @Override
    public String toString() {
        return "[T][ " + super.getStatusIcon() + " ] " + super.getDescription();
    }

    /**
     * Returns String format for ToDo tasks to be stored in text file
     * @return String format of ToDo tasks
     */
    @Override
    public String storeText() {
        return "[T]," + super.getStatus() + "," + super.getDescription() + ",";
    }
}
