package duke.task;

import duke.command.AddCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event is a subtype of task with description and period.
 */
public class Event extends Task {
    private LocalDateTime period;

    /**
     * A constructor create an event task using description and period.
     *
     * @param description A description of the task.
     * @param period The time period to do the task.
     */
    public Event(String description,LocalDateTime period) {
        super(description);
        this.category="event";
        this.period=period;
    }

    /**
     * A constructor creates an event from an command.
     *
     * @param addCommand An command which contains the description and time period of the task to add.
     */
    public Event(AddCommand addCommand){
        super(addCommand);
        this.period = LocalDateTime.parse(addCommand.getTimeNotes());
        this.period=period;
    }

    /**
     * Returns a string contains all the information of an event.
     *
     * @return A string includes type, status ,description and time period of the event.
     */
    @Override
    public String toString(){
        String taskType="[E]";
        FormatStyle dateFormat = FormatStyle.MEDIUM;
        FormatStyle timeFormat = FormatStyle.MEDIUM;
        String detail = taskType + " [" + getStatusIcon() + "] " + getDescription() +
                " (at: "+period.format(DateTimeFormatter.ofLocalizedDateTime(dateFormat,timeFormat))+")";
        return detail;
    }

    public LocalDateTime getPeriod() {
        return period;
    }
}
