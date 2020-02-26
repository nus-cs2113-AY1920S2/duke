package duke.parser;

import duke.common.DukeException;
import duke.command.*;
import duke.taskList.task.Deadline;
import duke.taskList.task.Event;
import duke.taskList.task.Todo;

import static duke.common.Constants.*;
import static duke.common.Constants.FORMAT_LENGTH;

public class Parser {
    public Parser() {

    }

    /**
     * Helps to check is a string is number
     * @param str input string
     * @return returns true if it is integer, else returns false
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * deals with making sense of the user command
     * @param fullCommand user input
     * @return returns different command type
     * @throws DukeException when user's command does not match with command standard
     */
    public static Command parse(String fullCommand) throws DukeException{
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
            String EventTime = fullCommand.substring(eventDividerPosition + FORMAT_LENGTH);
            return new AddCommand(new Event(eventName,EventTime));
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
        case LIST:
            return new ListCommand();
        case BYE:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }

    }

}
