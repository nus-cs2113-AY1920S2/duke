public class Event extends TaskManager {
    protected String by;
    public Event(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[E]" + super.toString();
        }
        return "[E]" + super.toString() + "( at: " + by + ")";
    }
}