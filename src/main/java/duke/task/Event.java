package duke.task;

import duke.command.AddCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private LocalDateTime period;

    public Event(String description,LocalDateTime period) {
        super(description);
        this.category="event";
        this.period=period;
    }

    public Event(AddCommand addCommand){
        super(addCommand);
        this.period = LocalDateTime.parse(addCommand.getTimeNotes());
    }

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
