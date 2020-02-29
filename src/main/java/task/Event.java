package task;

public class Event extends Task {

    protected String time;

    /**
     * Constructor of the Event class
     *
     * @param description description of the event
     * @param time time of the event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns the status and the description as a string prepended with "[E]"
     * and appended with "(at ...)"
     *
     * @return String of status and description of task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }

    /**
     * The description of the task to be saved
     *
     * @return Description of task and its status with T to indicate Event
     */
    @Override
    public String saveTask() {
        return "E|" + super.isDoneNum() + "|" + super.saveTask() + "|" + this.time;
    }
}
