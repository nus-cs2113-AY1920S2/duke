package Duke.Parser;

import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Todo;

/**
 * Parser for utility functions.
 */
public class ParserUtil {

    public static Todo createTodo(String userInput) throws DukeException {
        String description = userInput.substring("todo".length()).strip();
        if (description.isEmpty()) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }
        return new Todo(description);
    }

    public static Deadline createDeadline(String userInput) throws DukeException {
        String[] deadlineDetails = userInput.substring("deadline".length()).strip().split("/by");
        if (deadlineDetails.length != 2 || deadlineDetails[1] == null) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
        if (deadlineDetails[0].strip().isEmpty()) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }
        return new Deadline(deadlineDetails[0].strip(),deadlineDetails[1].strip());
    }

    public static Event createEvent(String userInput) throws DukeException {
        String[] eventDetails = userInput.substring("event".length()).strip().split("/at");

        if (eventDetails.length != 2 || eventDetails[1] == null) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
        if (eventDetails[0].strip().isEmpty()) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }
        return new Event(eventDetails[0].strip(), eventDetails[1].strip());
    }


}