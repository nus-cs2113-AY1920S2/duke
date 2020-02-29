package duke.tasks;

public class Deadline extends Task {
    /** Deadline date */
    protected String by;

    /**
     * Defines the constructor.
     * Fills in the task content and specifies the type of the task as "D" Deadline.
     * 
     * @param description Content of the task.
     * @param by The date by which the task should finish, i.e. the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Specifies the format when printing this task object.
     * Prints deadline date in "MMM dd yyyy" localdate format.
     *
     * @return Formatted string: [type][tick/cross] task content (by: deadline date).
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.type, super.toString(), by);
    }

    /**
     * Specifies the format of string that will be written into the txt file.
     * Saves deadline date in "yyyy-mm-dd" localdate format.
     *
     * @return Formatted string: type | status icon | task content | deadline date.
     */
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, by);
    }
}
