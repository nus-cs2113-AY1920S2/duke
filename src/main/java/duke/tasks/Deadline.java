package duke.tasks;

/**
 * Deadline inherits from Task and is the public class responsible for storing information about the task.
 */

public class Deadline extends Task {

    /**
     * The deadline/date entered by the user.
     */

    private String by;

    /**
     * Constructs the Deadline object.
     *
     * @param description the description of the deadline entered by the user.
     * @param by          the deadline/date entered by the user.
     */

    public Deadline(String description, String by) {
        super("[D]", description);
        this.by = by;
    }

    /**
     * Returns the information related to the Deadline object.
     *
     * @return the information related to the Deadline object.
     */

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
