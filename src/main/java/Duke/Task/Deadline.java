package duke.task;

/**
 * Class representing a Deadline Task.
 */
public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Retrieves the Deadline Description.
     * @return description
     */
    public String getDeadline() {
        return description;
    }

    @Override
    public String toString() {
        if (deadline.equals("")) {
            return "[D]" + super.toString();
        }
        return "[D]" + super.toString() + " ( by: " + deadline + " )";
    }
}
