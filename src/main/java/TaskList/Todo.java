package TaskList;

/**
 * Represents a task with the type Todo
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.itemType = 'T';
    }

    /**
     * Prints the content of the Todo type task
     */
    @Override
    public String printObject() {
        return ("[" + itemType + "][" + getStatusIcon() +"] "+ description);
    }

    /**
     * Reformat Todo task format for saving into file
     */
    @Override
    public String createStrForSaving() {
        return itemType + " | " + convertBoolean() + " | " + description;
    }
}
