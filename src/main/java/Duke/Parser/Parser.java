package Duke.Parser;

import Duke.Commands.*;
import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;

import static Duke.Library.Message.*;

public class Parser {

    public static Command parse(String userInput) throws DukeException {
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

    private static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }

    private static String getWord(String userInput) throws DukeException {
        try {
            return userInput.strip().split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }
}