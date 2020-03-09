package Duke.Tasks;

/**
 * A class of deadline task type with action to complete and date to complete it by.
 */
public class Deadline extends Task {

    /**
     * Constructor for Deadline class.
     *
     * @param action the task description.
     * @param by the date to complete the action by.
     */
    public Deadline(String action, String by) {
        super(action);
        this.taskType = "D";
        this.date = by;
    }

    /**
     * Creates a String with the Deadline task.
     *
     * @return String indicating task type, task description, and deadline date.
     */
    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + "(by: " + date + ")";
    }
}
