package Duke.Task;

/**
 * Class representing a Deadline Task.
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline(){
        return description;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[D]" + super.toString();
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
