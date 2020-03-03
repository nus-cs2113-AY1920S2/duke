package duke.task;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.HelpCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkDoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.library.ErrorMessage;
import duke.parser.Parser;
import duke.parser.ParserUtil;

import static duke.library.Message.COMMAND_BYE_WORD;
import static duke.library.Message.COMMAND_FIND_WORD;
import static duke.library.Message.COMMAND_DEADLINE_WORD;
import static duke.library.Message.COMMAND_TODO_WORD;
import static duke.library.Message.COMMAND_EVENT_WORD;
import static duke.library.Message.COMMAND_DELETE_WORD;
import static duke.library.Message.COMMAND_HELP_WORD;
import static duke.library.Message.COMMAND_LIST_WORD;
import static duke.library.Message.COMMAND_MARK_WORD;
import static duke.parser.Parser.getCommandWord;
import static duke.parser.Parser.getWord;

/**
 * Class contains the operations for the TaskList.
 */
public class TaskList {

    /**
     * Execute the command from userInput.
     *
     * @param userInput The userInput from the Ui.
     * @return The command object.
     * @throws DukeException If the command is undefined.
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
