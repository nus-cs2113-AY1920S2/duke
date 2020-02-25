package duke.task;

import duke.exception.DukeNullDateException;
import duke.exception.DukeNullDescriptionException;

import static duke.util.Constants.DEADLINE_ICON;

/**
 * This is the Deadline class inherited from the Task class.
 *
 * @author A11riseforme
 */
public class Deadline extends Task {
    protected String byDate;
    protected  String typeIcon;

    /**
     * Default constructor of Deadline class.
     *
     * @param description the description of the deadline task.
     * @param byDate the date of the deadline task.
     * @throws DukeNullDescriptionException exception is thrown if the description is empty.
     * @throws DukeNullDateException exception is thrown if the date is empty.
     */
    public Deadline(String description, String byDate) throws DukeNullDescriptionException, DukeNullDateException {
        super(description);
        typeIcon = DEADLINE_ICON;
        if (byDate.equals("")) {
            throw new DukeNullDateException();
        }
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
    public String getByDate() {
        return byDate;
    }

    /**
     * Cast the task to String type when called by System.out.println() or other similar methods.
     *
     * @return the String printout of the task.
     */
    @Override
    public String toString() {
        return typeIcon + super.toString() + " (by: " + byDate + ")";
    }
}
