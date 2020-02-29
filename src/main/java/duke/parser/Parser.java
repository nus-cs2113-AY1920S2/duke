package duke.parser;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.InvalidCommand;
import duke.command.ExitCommand;
import duke.command.AddCommand;
import duke.command.FindCommand;
import duke.command.CheckCommand;
import duke.common.DukeException;
import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Todo;

import static duke.common.Constants.LIST;
import static duke.common.Constants.DONE;
import static duke.common.Constants.TODO;
import static duke.common.Constants.TODO_LENGTH;
import static duke.common.Constants.DEADLINE;
import static duke.common.Constants.DEADLINE_LENGTH;
import static duke.common.Constants.EVENT;
import static duke.common.Constants.EVENT_LENGTH;
import static duke.common.Constants.DELETE;
import static duke.common.Constants.BYE;
import static duke.common.Constants.CHECK;
import static duke.common.Constants.FIND;
import static duke.common.Constants.FORMAT_LENGTH;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Checks if a string is integer.
     *
     * @param str Input string.
     * @return True if it is integer, else returns false.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Deals with making sense of the user command.
     *
     * @param fullCommand User input.
     * @return Returns different types of command.
     * @throws DukeException When user's command does not match with command standard.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] responses = fullCommand.split(" ");
        switch (responses[0]) {
        case TODO:
            if (responses.length < 2) {
                throw new DukeException("\tThe description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(fullCommand.substring(TODO_LENGTH)));
        case DEADLINE:
            if (responses.length < 2) {
                throw new DukeException("\tThe description of a deadline cannot be empty.");
            }
            int deadlineDividerPosition = fullCommand.indexOf(" /by");
            if (deadlineDividerPosition == -1) {
                throw new DukeException("\tPlease follow the format: deadline thingsToDo /by time");
            }
            String deadlineName = fullCommand.substring(DEADLINE_LENGTH,deadlineDividerPosition);
            String deadlineTime = fullCommand.substring(deadlineDividerPosition + FORMAT_LENGTH);
            return new AddCommand(new Deadline(deadlineName,deadlineTime));
        case EVENT:
            if (responses.length < 2) {
                throw new DukeException("\tThe description of a event cannot be empty.");
            }
            int eventDividerPosition = fullCommand.indexOf(" /at");
            if (eventDividerPosition == -1) {
                throw new DukeException("\tPlease follow the format: event thingsToDo /at time");
            }
            String eventName = fullCommand.substring(EVENT_LENGTH,eventDividerPosition);
            String eventTime = fullCommand.substring(eventDividerPosition + FORMAT_LENGTH);
            return new AddCommand(new Event(eventName,eventTime));
        case DONE:
            if (responses.length < 2 || !isNumeric(responses[1])) {
                throw new DukeException("\tPlease input a task number that you want to mark as done");
            }
            return new DoneCommand(Integer.parseInt(responses[1]));
        case DELETE:
            if (responses.length < 2 || !isNumeric(responses[1])) {
                throw new DukeException("\tPlease input a task number that you want to delete");
            }
            int deleteCount = Integer.parseInt(responses[1]);
            return new DeleteCommand(deleteCount);
        case FIND:
            if (responses.length < 2) {
                throw new DukeException("\tPlease input the thing you want to find.");
            }
            return new FindCommand(responses[1]);
        case CHECK:
            if (responses.length < 2) {
                throw new DukeException("\tPlease input the thing you want to check.");
            }
            return new CheckCommand(responses[1]);
        case LIST:
            return new ListCommand();
        case BYE:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}

