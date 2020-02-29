package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    /**
     * Defines the constructor.
     * Fills in the task content and specifies the type of the task as "D" Deadline.
     *
     * @param description Content of the task.
     * @param by The date by which the task should finish, i.e. the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
        this.type = "D";
    }

    /**
     * Specifies the format when printing this task object.
     * Prints deadline date in "MMM dd yyyy" localdate format.
     *
     * @return Formatted string: [type][tick/cross] task content (by: deadline date).
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.type, super.toString(), 
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.UK)));
    }

    /**
     * Specifies the format of string that will be written into the txt file.
     * Saves deadline date in "yyyy-mm-dd" localdate format.
     *
     * @return Formatted string: type | status icon | task content | deadline date.
     */
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, date);
    }
}
