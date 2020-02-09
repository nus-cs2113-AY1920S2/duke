package duke.task;

public class Event extends Task {
    protected String duration;

    public Event(String task, String duration) {
        super(task);
        this.duration = duration;
    }

    @Override
    public String getTaskStatus() {
        return "[E]" + super.getTaskStatus() + " (at: " + duration + ")";
    }
}
