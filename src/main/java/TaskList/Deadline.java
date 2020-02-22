package TaskList;

import java.io.FileNotFoundException;

/**
 * Represents a task with deadline
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.itemType = 'D';
    }

    /**
     * Prints the content of the Deadline type task
     */
    @Override
    public String printObject() {
        return ("[" + itemType + "][" + getStatusIcon() + "] "+ description + " (by: " + by + ")");
    }

    /**
     * Reformat Deadline task format for saving into file
     */
    @Override
    public String createStrForSaving() {
        return itemType + " | " + convertBoolean() + " | " + description + " | " + by;
    }
}
