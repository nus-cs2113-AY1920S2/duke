package chatty.parser;

import chatty.command.ByeCommand;
import chatty.command.Command;
import chatty.command.DeadlineCommand;
import chatty.command.DeleteCommand;
import chatty.command.DoneCommand;
import chatty.command.EventCommand;
import chatty.command.ListCommand;
import chatty.command.TodoCommand;
import chatty.exception.ChattyChatBotException;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static chatty.util.Constants.AT_STRING;
import static chatty.util.Constants.BYE_STRING;
import static chatty.util.Constants.BY_STRING;
import static chatty.util.Constants.DEADLINE_STRING;
import static chatty.util.Constants.DELETE_STRING;
import static chatty.util.Constants.DONE_STRING;
import static chatty.util.Constants.EVENT_STRING;
import static chatty.util.Constants.LIST_STRING;
import static chatty.util.Constants.SPACE_SEPARATOR;
import static chatty.util.Constants.TODO_STRING;
import static chatty.util.Constants.TO_STRING;

public class Parser {

    public Command parseCommand(String userInput) throws ChattyChatBotException, ArrayIndexOutOfBoundsException {
        // Solution below adapted from: https://stackoverflow
        // .com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
        String[] array = userInput.split(SPACE_SEPARATOR, 2);
        String commandType = array[0];

        switch (commandType) {
        case LIST_STRING:
            return new ListCommand();
        case DONE_STRING:
            return new DoneCommand(Integer.parseInt(array[1]) - 1);
        case TODO_STRING:
            return new TodoCommand(array[1]);
        case DEADLINE_STRING:
            String deadlineStr = array[1];
            String[] deadlineFields = deadlineStr.split(BY_STRING);
            if (deadlineFields.length != 2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            return new DeadlineCommand(deadlineFields[0],
                    LocalDate.parse(deadlineFields[1].trim(), DateTimeFormatter.ISO_DATE));
        case EVENT_STRING:
            String eventStr = array[1];
            String[] eventFields = eventStr.split(AT_STRING);
            if (eventFields.length != 2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            String[] times = eventFields[1].split(TO_STRING);
            return new EventCommand(eventFields[0], LocalDate.parse(times[0].trim(), DateTimeFormatter.ISO_DATE),
                    LocalDate.parse(times[1].trim(), DateTimeFormatter.ISO_DATE));
        case DELETE_STRING:
            return new DeleteCommand(Integer.parseInt(array[1]) - 1);
        case BYE_STRING:
            return new ByeCommand();
        default:
            throw new ChattyChatBotException();
        }
    }
}
