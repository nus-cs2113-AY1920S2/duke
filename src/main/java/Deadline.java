public class Deadline extends Task {

    protected String by;

    /**
     * Returns the task created with the specified
     * description.
     *
     * @param description the description of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
