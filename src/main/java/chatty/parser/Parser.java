package chatty.parser;

import chatty.command.ByeCommand;
import chatty.command.Command;
import chatty.command.DateCommand;
import chatty.command.DeadlineCommand;
import chatty.command.DeleteCommand;
import chatty.command.DoneCommand;
import chatty.command.EventCommand;
import chatty.command.FindCommand;
import chatty.command.ListCommand;
import chatty.command.TodoCommand;
import chatty.exception.ChattyChatBotDateCommandException;
import chatty.exception.ChattyChatBotDeadlineCommandException;
import chatty.exception.ChattyChatBotDeleteCommandException;
import chatty.exception.ChattyChatBotDoneCommandException;
import chatty.exception.ChattyChatBotEventCommandException;
import chatty.exception.ChattyChatBotException;
import chatty.exception.ChattyChatBotFindCommandException;
import chatty.exception.ChattyChatBotTodoCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static chatty.util.Constants.AT_STRING;
import static chatty.util.Constants.BYE_STRING;
import static chatty.util.Constants.BY_STRING;
import static chatty.util.Constants.DATE_STRING;
import static chatty.util.Constants.DEADLINE_STRING;
import static chatty.util.Constants.DELETE_STRING;
import static chatty.util.Constants.DONE_STRING;
import static chatty.util.Constants.EVENT_STRING;
import static chatty.util.Constants.FIND_STRING;
import static chatty.util.Constants.LIST_STRING;
import static chatty.util.Constants.SPACE_SEPARATOR;
import static chatty.util.Constants.TODO_STRING;
import static chatty.util.Constants.TO_STRING;

/**
 * Handles parsing of user input.
 */
public class Parser {

    /**
     * Parses user input and returns the parsed command.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed command.
     * @throws ChattyChatBotException         Throws ChattyChatBotException if user input does not match any command.
     * @throws ArrayIndexOutOfBoundsException Throws ArrayIndexOutOfBoundsException if there are not enough fields
     *                                        when parsing deadline or event command.
     */
    public Command parseCommand(String userInput) throws ChattyChatBotException, ArrayIndexOutOfBoundsException {
        // Solution below adapted from: https://stackoverflow
        // .com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
        String[] array = userInput.split(SPACE_SEPARATOR, 2);
        String commandType = array[0];

        switch (commandType) {
        case LIST_STRING:
            return new ListCommand();
        case DONE_STRING:
            try {
                return new DoneCommand(Integer.parseInt(array[1]) - 1);
            } catch (Exception e) {
                throw new ChattyChatBotDoneCommandException();
            }
        case TODO_STRING:
            try {
                return new TodoCommand(array[1]);
            } catch (Exception e) {
                throw new ChattyChatBotTodoCommandException();
            }
        case DEADLINE_STRING:
            try {
                String deadlineStr = array[1];
                String[] deadlineFields = deadlineStr.split(BY_STRING);
                return new DeadlineCommand(deadlineFields[0],
                        LocalDate.parse(deadlineFields[1].trim(), DateTimeFormatter.ISO_DATE));
            } catch (Exception e) {
                throw new ChattyChatBotDeadlineCommandException();
            }
        case EVENT_STRING:
            try {
                String eventStr = array[1];
                String[] eventFields = eventStr.split(AT_STRING);
                String[] times = eventFields[1].split(TO_STRING);
                return new EventCommand(eventFields[0], LocalDate.parse(times[0].trim(), DateTimeFormatter.ISO_DATE),
                        LocalDate.parse(times[1].trim(), DateTimeFormatter.ISO_DATE));
            } catch (Exception e) {
                throw new ChattyChatBotEventCommandException();
            }
        case DELETE_STRING:
            try {
                return new DeleteCommand(Integer.parseInt(array[1]) - 1);
            } catch (Exception e) {
                throw new ChattyChatBotDeleteCommandException();
            }
        case FIND_STRING:
            try {
                return new FindCommand(array[1]);
            } catch (Exception e) {
                throw new ChattyChatBotFindCommandException();
            }
        case DATE_STRING:
            try {
                return new DateCommand(LocalDate.parse(array[1], DateTimeFormatter.ISO_DATE));
            } catch (Exception e) {
                throw new ChattyChatBotDateCommandException();
            }
        case BYE_STRING:
            return new ByeCommand();
        default:
            throw new ChattyChatBotException();
        }
    }
}
