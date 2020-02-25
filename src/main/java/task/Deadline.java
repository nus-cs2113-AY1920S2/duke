package task;

/**
 * Represents a task of the type Deadline.
 * A Deadline object contains the required date of completion in addition to information contained in its parent class, Task.
 * @see Task
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    @Override
    public String getFileRecord() {
        int doneValue = isDone ? 1 : 0;
        return String.format("%s,%d,%s,%s\n", this.taskType, doneValue, this.description.strip(), this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}