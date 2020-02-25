package task;

/**
 * Represents a task of the type Event.
 * An Event object contains the time frame of the event in addition to information contained in its parent, Task.
 * @see Task
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "E";
    }

    @Override
    public String getFileRecord() {
        int doneValue = isDone ? 1 : 0;
        return String.format("%s,%d,%s,%s\n", this.taskType, doneValue, this.description.strip(), this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
