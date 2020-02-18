/**
 * Represents ToDo tasks
 * An ToDo object corresponds to ToDo description
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
     * Returns string format for displaying on CLI
     * @return  String representation for ToDo
     */
    @Override
    public String toString() {
        return "[T][ " + super.getStatusIcon() + " ] " + super.getDescription();
    }

    /**
     * Returns the ToDo object's details , separated by commas
     * @return String format to be stored in text file
     */
    @Override
    public String storeText() {
        return "[T]," + super.getStatus() + "," + super.getDescription() + ",";
    }
}
