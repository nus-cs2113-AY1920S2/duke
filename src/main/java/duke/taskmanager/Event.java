package duke.taskmanager;

public class Event extends Task {
    protected String by;
    public Event(String task, String by) {
        super(task);
        this.by = by;
        isDone = false;
    }

    @Override
    public String getTask() {
        return task + by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[E]" + super.toString();
        }
        return "[E]" + super.toString() + "( at: " + by + ")";
    }

    @Override
    public String contentToFile() { return "E" + "|" +
            super.contentToFile() + "|" + by; }
}