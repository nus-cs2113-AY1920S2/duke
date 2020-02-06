package duke.exception;
import org.jetbrains.annotations.NotNull;

public class FindDukeException extends Throwable {
    private String cmd;
    private String[] words;
    private boolean isWrongLength;

    public FindDukeException (@NotNull String cmd) {
        this.cmd = cmd;
        this.words = cmd.split(" ");
        this.isWrongLength = words.length <= 1;
    }

    public void toDoException () throws DukeException {
        if (isWrongLength) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty. \uD83E\uDD7A");
        }
    }

    public void deadlineException () throws DukeException {
        boolean isMissingTime = (!cmd.contains("/by"));
        if (isWrongLength) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty. \uD83E\uDD7A");
        } else if (isMissingTime) {
            throw new DukeException("OOPS!!! You forget to fill in a specific date/time. \uD83E\uDD7A");
        }
    }

    public void eventException () throws DukeException {
        boolean isMissingTime = (!cmd.contains("/at"));
        if (isWrongLength) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty. \uD83E\uDD7A");
        } else if (isMissingTime) {
            throw new DukeException("OOPS!!! You forget to fill in a specific date/time. \uD83E\uDD7A");
        }
    }

    public void undefinedTypeException () throws DukeException{
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.. \uD83E\uDD7A");
    }
}
