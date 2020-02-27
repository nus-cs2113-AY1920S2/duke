package duke.tasks;

/**
 * Class to represent a todo task
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * get the string representation of this todo
     * @return string representation of this todo
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    /**
     * get the string representation of this todo formatted for saving to file
     * @return the string representation of this todo formatted for saving to file
     */
    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "T," + done + "," + description;
    }
}
