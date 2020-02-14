public class Event extends Task {

    protected String at;

    /**
     * Returns the task created with the specified
     * description.
     *
     * @param description the description of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
