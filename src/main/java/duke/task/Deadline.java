package duke.task;

/**
 * Represents a type deadline, an extension of the Task class. A deadline object is a type
 * of Task that has to be completed by a certain time for the user, and can be specificed
 * using /by, e.g. 'return book /by March 3rd'
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
