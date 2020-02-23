public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        setType('E');
        setAt(at);
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }
}
