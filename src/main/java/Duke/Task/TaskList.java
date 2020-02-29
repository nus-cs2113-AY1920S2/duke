package Duke.Task;

import Duke.Commands.*;
import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;
import Duke.Parser.ParserUtil;

import static Duke.Library.Message.*;
import static Duke.Parser.Parser.getCommandWord;

/**
 * Parser for duke.commands entered by the duke.Duke user. It reads from standard input and
 * returns Command objects.
 */
public class TaskList {

    public static Command executeCommand(String userInput) throws DukeException {
        String commandWord = getCommandWord(userInput);
        switch (commandWord.toUpperCase()) {
            case COMMAND_HELP_WORD:
                return new HelpCommand();
            case COMMAND_BYE_WORD:
                return new ExitCommand();
            case COMMAND_TODO_WORD:
                return new AddCommand(ParserUtil.createTodo(userInput));
            case COMMAND_DEADLINE_WORD:
                return new AddCommand(ParserUtil.createDeadline(userInput));
            case COMMAND_EVENT_WORD:
                return new AddCommand(ParserUtil.createEvent(userInput));
            case COMMAND_LIST_WORD:
                return new ListCommand();
            case COMMAND_MARK_WORD:
                return new MarkDoneCommand(ParserUtil.getIndex(userInput));
            case COMMAND_DELETE_WORD:
                return new DeleteCommand(ParserUtil.getIndex(userInput));
            default:
                throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }
    }

}
