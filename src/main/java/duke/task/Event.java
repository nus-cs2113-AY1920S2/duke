package duke.task;

import duke.command.AddCommand;

/**
 * Event is a subtype of task with description and period.
 */
public class Event extends Task {
    protected String period;

    /**
     * A constructor create an event task using description and period.
     *
     * @param description A description of the task.
     * @param period The time period to do the task.
     */
    public Event(String description,String period) {
        super(description);
        this.period=period;
    }

    /**
     * A constructor creates an event from an command.
     *
     * @param addCommand An command which contains the description and time period of the task to add.
     */
    public Event(AddCommand addCommand){
        super(addCommand);
        this.period = addCommand.getTimeNotes();
    }

    /**
     * Returns a string contains all the information of an event.
     *
     * @return A string includes type, status ,description and time period of the event.
     */
    @Override
    public String toString(){
        String taskType="[E]";
        String detail = taskType + " [" + getStatusIcon() + "] " + description+" (at: "+period+")";
        return detail;
    }
}
