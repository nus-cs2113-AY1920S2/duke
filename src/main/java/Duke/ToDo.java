package Duke;

/**
 * Represents a task object with an item that needs to be done, but does not have a specific date to be completed by.
 */
public class ToDo extends Task {

    /**
     * Constructor for a new todo item.
     * @param description the description of the ToDo item
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for a todo task when reading from a saved file.
     * @param description description of the ToDo item
     * @param status the status of the ToDO item
     */
    public ToDo(String description, String status) {
        super(description);
        if (status.equals("1")) {
            this.markAsDone();
        }
    }

    /**
     * @return a string containing the status and the description of the todo item for printing.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * @return @return a string of information of the todo item in the format for saving to a file
     */
    @Override
    public String toSaveFormat() {
        return(super.toSaveFormat() + "T");
    }
}
