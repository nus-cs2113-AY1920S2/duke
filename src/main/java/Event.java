public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        setType('E');
        setAt(at);
    }

    /**
     * Returns when the event is at.
     * @return the date and time of event
     */
    public String getAt() {
        return at;
    }

    /**
     * Sets when the event is at.
     * @param at the date and time of event
     */
    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }
}
