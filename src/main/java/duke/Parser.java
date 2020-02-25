package duke;

import duke.command.*;
import duke.exception.DukeException;

/* parser class that is used to deal with making sense of user commands */
public class Parser {

    /* split user command into different parts and decide which operation should be implement on the task list */
    public static Command parse(String commandLine) throws DukeException {
        String[] attributes = commandLine.split(" ", 2);
        attributes[0] = attributes[0].toLowerCase();
        switch (attributes[0]){
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "done":
            try {
                return new DoneCommand(Integer.parseInt(attributes[1]));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException(Ui.INVALID_TASKNO +Ui.DONE_DESCRIPTION);
            }
        case "todo":
            try {
                return new AddTodoCommand(attributes[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.INVALID_DESCRIPTION+Ui.TODO_DESCRIPTION);
            }
        case "deadline":
            try {
                return new AddDeadlineCommand(attributes[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.INVALID_DESCRIPTION+Ui.DEADLINE_DESCRIPTION);
            }
        case "event":
            try {
                return new AddEventCommand(attributes[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.INVALID_DESCRIPTION+Ui.EVENT_DESCRIPTION);
            }
        case "delete":
            try {
                return new DeleteCommand(Integer.parseInt(attributes[1]));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException(Ui.INVALID_TASKNO +Ui.DELETE_DESCRIPTION);
            }
        case "help": return new HelpCommand();
        default: throw new DukeException(Ui.INVALID_COMMAND);
        }
    }
}
