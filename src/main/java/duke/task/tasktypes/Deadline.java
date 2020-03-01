package duke.task.tasktypes;

/**
 * A class representing a deadline task.
 */
public class Deadline extends Task {

    /** Date to complete deadline by */
    private String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;

        this.taskType = TaskType.D;
    }

    public String getBy () {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", taskType, super.toString(), by);
    }
}
