package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task of the type Deadline.
 * A Deadline object contains the required date of completion in addition to information contained in its parent class, Task.
 * @see Task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline Task class.
     * <p> <br>
     * Creates a new Deadline with the task description and deadline.
     * Also sets the taskType to "D", representing Deadline.
     *</p>
     * @param description Description of the task provided by the user.
     * @param by Deadline of the task provided by the user.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    @Override
    public String getFileRecord() {
        int doneValue = isDone ? 1 : 0;
        return String.format("%s,%d,%s,%s\n", taskType, doneValue, description.strip(), by);
    }

    /**
     * Returns task description for displaying of task details.
     * Allows re-formatting of deadline description from yyyy-mm-dd to mm-d-yyyy.
     *
     * @return String of re-formatted task details.
     */
    @Override
    public String toString() {
        LocalDate date = null;
        try {
            date = LocalDate.parse(by); // Parse input of form yyyy-mm-dd
        } catch (DateTimeParseException e){
            return "[D]" + super.toString() + " (by: " + by + ")"; // If not in correct date format, print original String
        }
        String parsedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + parsedDate + ")";
    }
}