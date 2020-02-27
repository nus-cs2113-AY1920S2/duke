package tasks;

/**
 * <h1>Event</h1>
 * Event tasks are tasks that have a specific date/time
 * e.g., carnival /at 11 May 5pm
 * Contains Event-specific descriptions
 */
public class Event extends Task {
    protected String dateStr;

    public Event(String taskName, String dateStr) {
        super(taskName);
        this.taskType = 'E';
        this.dateStr = dateStr;
    }

    /**
     * Not yet implemented: A branch-8 task (TODO)
     * Gets the date of the event
     * @return the date as a String
     */
    public String getDateStr() {
        return dateStr;
    }

    /**
     * Not yet implemented: A branch-8 task (TODO)
     * Sets the date of the task
     * @param dateStr the given date as a String
     */
    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    /**
     * Converts the Event task to a String-readable format
     * @return a String containing the task details, includes the date
     */
    @Override
    public String toString() {
        return (super.toString() + " (at: " + dateStr +")" );
    }
}
