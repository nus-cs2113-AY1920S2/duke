package data;

/**
 * This class represents the EVENT subtype of Tasks that can be stored in Duke. It contains a description and a deadline.
 */
public class Event extends Task {

    public static final int EVENT_NUMBER_OF_FIELDS = 4;
    public static final String EVENT_NOTATION = "E";
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

    /**
     * This method converts the data for an EVENT object into a String array for easy parsing and application.
     * @return a String array consisting of the initial denoting the Task, its completion status and the description
     */
    @Override
    public String[] getTaskData(){
        String[] taskValues = new String[EVENT_NUMBER_OF_FIELDS];
        taskValues[0] = EVENT_NOTATION;
        if (this.getIsDone()) {
            taskValues[1] = TASK_DONE_NOTATION;
        } else {
            taskValues[1] = TASK_NOTDONE_NOTATION;
        }
        taskValues[2] = this.getDescription();
        taskValues[3] = this.getBy();
        return taskValues;
    }
}
