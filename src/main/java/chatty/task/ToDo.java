package chatty.task;

/**
 * Class for todo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for todo task.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the todo task to string.
     *
     * @return String representing the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Converts the todo task to a string to be stored in file.
     *
     * @return String representing the todo task which should be stored in file.
     */
    @Override
    public String getFileString() {
        return String.format("T|%s|%s", this.getDoneStatus(), this.getDescription());
    }
}
