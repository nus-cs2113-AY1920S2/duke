package duke.task;

import duke.exception.DukeNullDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.util.Constants.EVENT_ICON;

/**
 * This is the Event class inherited from the Task class.
 *
 * @author A11riseforme
 */
public class Event extends Task {
    protected LocalDate atDate;
    protected String typeIcon;

    /**
     * Default constructor of Event class.
     *
     * @param description the description of the event task.
     * @param atDate the date of the event task.
     * @throws DukeNullDescriptionException exception is thrown if the description is empty.
     */
    public Event(String description, LocalDate atDate) throws DukeNullDescriptionException {
        super(description);
        typeIcon = EVENT_ICON;
        this.atDate = atDate;
    }

    /**
     * Get the Icon representation of the event task.
     *
     * @return a icon string `[E]` which represents the event task.
     */
    public String getIcon() {
        return typeIcon;
    }

    /**
     * Get the date of the event task.
     *
     * @return a LocalDate of the date of the event task.
     */

    public LocalDate getAtDate() {
        return atDate;
    }

    /**
     * Cast the task to String type when called by System.out.println() or other similar methods.
     *
     * @return the String printout of the task.
     */
    @Override
    public String toString() {
        return typeIcon + super.toString() + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
