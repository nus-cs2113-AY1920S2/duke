public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setType('D');
        setBy(by);
    }

    /**
     * Returns the deadline of the task.
     * @return the deadline of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the deadline of the task.
     * @param by the deadline of the task
     */
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }
}
