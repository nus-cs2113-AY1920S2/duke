package duke.tasks;

/**
 * To-Do inherits from Task and is the public class responsible for storing information about the task.
 */

public class ToDo extends Task {

    /**
     * Constructs the to-do object.
     *
     * @param description the description of the to-do entered by the user.
     */

    public ToDo(String description) {
        super("[T]", description);
    }

    /**
     * Returns the information related to the to-do object.
     *
     * @return the information related to the to-do object.
     */

    @Override
    public String toString() {
        return super.toString();
    }
}
