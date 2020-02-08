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
        String[] bySplit = by.split(" ", 2);
        return "[E]" + super.toString() + "(" + bySplit[0] + ": " + bySplit[1] + ")";
    }
}