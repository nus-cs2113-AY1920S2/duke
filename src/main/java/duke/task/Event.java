package duke.task;

import duke.format.DateTime;

public class Event extends Task {
    protected DateTime duration;

    public Event(String task, DateTime duration) {
        super(task);
        this.duration = duration;
    }

    @Override
    public DateTime getDateTime() {
        return duration;
    }

    @Override
    public String getTaskStatus() {
        return "[E]" + super.getTaskStatus() + " (at: " + duration + ")";
    }
}
