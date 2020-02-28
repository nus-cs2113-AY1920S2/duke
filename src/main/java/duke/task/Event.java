package duke.task;

/**
 * Represents a task of the type Event.
 * An Event object contains the time frame of the event in addition to information contained in its parent, Task.
 * @see Task
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event Task class.
     * <p> <br>
     * Creates a new Event with the task description and event period.
     * Also sets the taskType to "E", representing Event.
     *</p>
     * @param description Description of the task provided by the user.
     * @param at Event period specified by the user.
     */
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

    /**
     * Returns task description for displaying of task details.
     *
     * @return String of re-formatted task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
