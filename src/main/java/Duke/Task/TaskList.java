package Duke.Task;

import Duke.Commands.*;
import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;
import Duke.Parser.Parser;
import Duke.Parser.ParserUtil;

import static Duke.Library.Message.*;
import static Duke.Parser.Parser.getCommandWord;
import static Duke.Parser.Parser.getWord;

/**
 * Class contains the operations for the TaskList
 */
public class TaskList {

    /**
     * Reads the userInput and return a Command object.
     *
     * @param userInput
     * @return
     * @throws DukeException
     */
    public static Command executeCommand(String userInput) throws DukeException {

        String commandWord = getCommandWord(userInput);
        switch (commandWord.toUpperCase()) {
            case COMMAND_FIND_WORD:
                return new FindCommand(getWord(userInput));
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
                return new MarkDoneCommand(Parser.getIndex(userInput));
            case COMMAND_DELETE_WORD:
                return new DeleteCommand(Parser.getIndex(userInput));
            default:
                throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }
    }

}
