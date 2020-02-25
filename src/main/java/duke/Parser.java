package duke;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {

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
        case "find":
            try {
                return new FindCommand(attributes[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.LACK_KEYWORD+Ui.FIND_DESCRIPTION);
            }
        default: throw new DukeException(Ui.INVALID_COMMAND);
        }
    }
}
