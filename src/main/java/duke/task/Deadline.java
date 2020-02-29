package duke.task;

import duke.command.AddCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description,LocalDateTime by) {
        super(description);
        this.category="deadline";
        this.by=by;
    }

    public Deadline(AddCommand addCommand){
        super(addCommand);
        this.by = LocalDateTime.parse(addCommand.getTimeNotes());
    }

    @Override
    public String toString(){
        String taskType="[D]";
        FormatStyle dateFormat = FormatStyle.MEDIUM;
        FormatStyle timeFormat = FormatStyle.MEDIUM;
        String detail = taskType + " [" + getStatusIcon() + "] " + getDescription() +
                " (by: "+by.format(DateTimeFormatter.ofLocalizedDateTime(dateFormat,timeFormat))+")";
        return detail;
    }

    public LocalDateTime getBy() {
        return by;
    }
}
