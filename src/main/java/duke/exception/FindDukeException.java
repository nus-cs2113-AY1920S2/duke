package duke.exception;
import org.jetbrains.annotations.NotNull;

/**
 * Find exceptions when Duke is running.
 */

public class FindDukeException extends Throwable {
    private String cmd;
    private String[] words;
    private boolean isWrongLength;

    public FindDukeException (@NotNull String cmd) {
        this.cmd = cmd;
        this.words = cmd.split(" ");
        this.isWrongLength = words.length <= 1;
    }

    /**
     * Detect exception when todo command be processed.
     * @throws DukeException if todo's task is missing.
     */
    public void toDoException () throws DukeException {
        if (isWrongLength) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Detect exception when deadline command be processed.
     * @throws DukeException if deadline's task or time is missing.
     */
    public void deadlineException () throws DukeException {
        boolean isMissingTime = (!cmd.contains("/by"));
        if (isWrongLength) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (isMissingTime) {
            throw new DukeException("OOPS!!! You forget to fill in a specific date/time.");
        }
    }

    /**
     * Detect exception when event command be processed.
     * @throws DukeException if event's task or time is missing.
     */
    public void eventException () throws DukeException {
        boolean isMissingTime = (!cmd.contains("/at"));
        if (isWrongLength) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (isMissingTime) {
            throw new DukeException("OOPS!!! You forget to fill in a specific date/time.");
        }
    }

    /**
     * Detect exception when command type can't be defined.
     * @throws DukeException
     */
    public void undefinedTypeException () throws DukeException{
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means..");
    }
}
