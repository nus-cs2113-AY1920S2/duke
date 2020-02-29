package duke.tasks;

public class Event extends Task {
    /** Event date */
    protected String at;

    /**
     * Defines the constructor.
     * Fills in the task content and specifies the type of the task as "E" Event.
     *
     * @param description Content of the task.
     * @param at The date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    /**
     * Specifies the format when printing this task object.
     * Prints event date in "MMM dd yyyy" localdate format.
     *
     * @return Formatted string: [type][tick/cross] task content (at: event date).
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", this.type, super.toString(), at);
    }

    /**
     * Specifies the format of string that will be written into the txt file.
     * Saves event date in "yyyy-mm-dd" localdate format.
     *
     * @return Formatted string: type | status icon | task content | event date.
     */
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, at);
    }
}
