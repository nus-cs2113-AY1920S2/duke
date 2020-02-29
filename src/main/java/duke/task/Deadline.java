package duke.task;

import duke.exception.DukeNullDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.util.Constants.DEADLINE_ICON;

/**
 * This is the Deadline class inherited from the Task class.
 *
 * @author A11riseforme
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected  String typeIcon;

    /**
     * Default constructor of Deadline class.
     *
     * @param description the description of the deadline task.
     * @param byDate the date of the deadline task.
     * @throws DukeNullDescriptionException exception is thrown if the description is empty.
     */
    public Deadline(String description, LocalDate byDate) throws DukeNullDescriptionException {
        super(description);
        typeIcon = DEADLINE_ICON;
        this.byDate = byDate;
    }

    /**
     * Get the Icon representation of the deadline task.
     *
     * @return a icon string `[D]` which represents the deadline task.
     */
    public String getIcon() {
        return typeIcon;
    }

    /**
     * Get the date of the deadline task.
     *
     * @return a String of the date of the deadline task.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Cast the task to String type when called by System.out.println() or other similar methods.
     *
     * @return the String printout of the task.
     */
    @Override
    public String toString() {
        return typeIcon + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
