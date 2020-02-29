package duke.task;

import duke.command.AddCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Deadline is a subtype of task which have a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * A constructor creates a new deadline with description and a deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description,LocalDateTime by) {
        super(description);
        this.category="deadline";
        this.by=by;
    }

    /**
     * A constructor creates a new deadline according to a command.
     *
     * @param addCommand A command includes description and a deadline of a task.
     */
    public Deadline(AddCommand addCommand){
        super(addCommand);
        this.by = LocalDateTime.parse(addCommand.getTimeNotes());
    }

    /**
     * Returns a string contains all the information of an deadline task.
     *
     * @return A string includes type, status ,description and deadline of the deadline task.
     */
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
