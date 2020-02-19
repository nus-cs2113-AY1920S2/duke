package Duke.Task;

public class Event extends Todo {

    protected String by;

    public Event(String description, String by, int index) {
        super(description, index);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + by + ")";
    }
}
