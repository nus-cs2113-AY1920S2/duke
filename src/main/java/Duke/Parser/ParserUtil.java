package duke.parser;

import duke.exception.DukeException;
import duke.library.ErrorMessage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser for utility functions.
 */
public class ParserUtil {

    /**
     * Create a todo task.
     *
     * @param userInput The userInput from Ui.
     * @return Todo Object.
     * @throws DukeException If the userInput is undefined.
     */

    public static Todo createTodo(String userInput) throws DukeException {
        String description = userInput.substring("todo".length()).strip();
        if (description.isEmpty()) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }
        return new Todo(description);
    }

    /**
     * Create a deadline task.
     *
     * @param userInput The userInput from Ui.
     * @return Deadline Object.
     * @throws DukeException If the userInput is undefined.
     */

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

    /**
     * Create an event task.
     *
     * @param userInput The userInput from Ui.
     * @return Event Object.
     * @throws DukeException If the userInput is undefined.
     */

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