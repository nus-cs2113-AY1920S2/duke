package duke.util;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeDateFormatException;
import duke.exception.DukeException;
import duke.exception.DukeNullDateException;
import duke.exception.DukeNullDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static duke.util.Constants.DEADLINE_COMMAND;
import static duke.util.Constants.DEADLINE_COMMAND_SHORTCUT;
import static duke.util.Constants.DEADLINE_FORMAT_ERROR_MESSAGE;
import static duke.util.Constants.DEADLINE_TIME_DELIMITER;
import static duke.util.Constants.DELETE_COMMAND;
import static duke.util.Constants.DELETE_COMMAND_SHORTCUT;
import static duke.util.Constants.DONE_COMMAND;
import static duke.util.Constants.EVENT_COMMAND;
import static duke.util.Constants.EVENT_COMMAND_SHORTCUT;
import static duke.util.Constants.EVENT_FORMAT_ERROR_MESSAGE;
import static duke.util.Constants.EVENT_TIME_DELIMITER;
import static duke.util.Constants.EXIT_COMMAND_BYE;
import static duke.util.Constants.EXIT_COMMAND_EXIT;
import static duke.util.Constants.EXIT_COMMAND_QUIT;
import static duke.util.Constants.HELP_COMMAND;
import static duke.util.Constants.LIST_COMMAND;
import static duke.util.Constants.LIST_COMMAND_SHORTCUT;
import static duke.util.Constants.TODO_COMMAND;
import static duke.util.Constants.TODO_COMMAND_SHORTCUT;
import static duke.util.Constants.UNKNOWN_COMMAND_RESPONSE;

/**
 * Parser class to parse the users' input.
 *
 * @author A11riseforme
 */
public class Parser {
    /**
     * parse the users' input.
     *
     * This method try to identify the command user intends to call, and parse the full command into different parts.
     * Then pass required arguments to the command.
     *
     * @param userInput the entire input from the user.
     * @return a Command object corresponding to the command the user intends to call.
     * @throws DukeException exception is thrown when task description is empty, the required date is
     * empty or the format is wrong.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] commands = splitCommand(userInput);
        String commandWord = commands[0];
        String afterCommand = commands[1];
        switch (commandWord) {
        case EXIT_COMMAND_BYE:
        case EXIT_COMMAND_EXIT:
        case EXIT_COMMAND_QUIT:
            return new ExitCommand();
        case LIST_COMMAND:
        case LIST_COMMAND_SHORTCUT:
            return new ListCommand();
        case DONE_COMMAND:
            int doneTaskId = extractTaskId(userInput);
            return new DoneCommand(doneTaskId);
        case TODO_COMMAND:
        case TODO_COMMAND_SHORTCUT:
            return new TodoCommand(afterCommand);
        case DEADLINE_COMMAND:
        case DEADLINE_COMMAND_SHORTCUT:
            try {
                String taskDescriptions = extractTaskDescription(afterCommand, DEADLINE_TIME_DELIMITER);
                String taskDateString = extractTaskDate(afterCommand, DEADLINE_TIME_DELIMITER);
                LocalDate taskDate = parseStringToDate(taskDateString);
                return new DeadlineCommand(taskDescriptions, taskDate);
            } catch (DukeNullDescriptionException e) {
                throw new DukeNullDescriptionException();
            } catch (DukeDateFormatException e) {
                throw new DukeDateFormatException();
            } catch (DukeException e) {
                throw new DukeException(DEADLINE_FORMAT_ERROR_MESSAGE);
            }
        case EVENT_COMMAND:
        case EVENT_COMMAND_SHORTCUT:
            try {
                String taskDescriptions = extractTaskDescription(afterCommand, EVENT_TIME_DELIMITER);
                String taskDateString = extractTaskDate(afterCommand, EVENT_TIME_DELIMITER);
                LocalDate taskDate = parseStringToDate(taskDateString);
                return new EventCommand(taskDescriptions, taskDate);
            } catch (DukeNullDescriptionException e) {
                throw new DukeNullDescriptionException();
            } catch (DateTimeParseException e) {
                throw new DukeDateFormatException();
            } catch (DukeException e) {
                throw new DukeException(EVENT_FORMAT_ERROR_MESSAGE);
            }
        case DELETE_COMMAND:
        case DELETE_COMMAND_SHORTCUT:
            int delTaskId = extractTaskId(userInput);
            return new DeleteCommand(delTaskId);
        case HELP_COMMAND:
            return new HelpCommand(afterCommand);
        default:
            throw new DukeException(UNKNOWN_COMMAND_RESPONSE);
        }
    }

    /**
     * Split user's input into two parts, the first part is the command word, the second part is the rest
     *
     * @param userInput the entire input from the user.
     * @return an array of two Strings, command word and the rest.
     */
    private static String[] splitCommand(String userInput) {
        String commandWord = extractCommandWord(userInput);
        String afterCommand = userInput.substring(commandWord.length());
        String[] commands = new String[]{commandWord, afterCommand};
        return commands;
    }

    /**
     * Extract the command word from the user input.
     *
     * @param userInput the entire input from the user.
     * @return the command word which the user intend to call.
     */
    private static String extractCommandWord(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        if (spaceIndex == -1) {
            return userInput;
        }
        return userInput.substring(0, spaceIndex);
    }

    /**
     * Extract the task id from the user input which intends to call `done` command
     *
     * @param userInput the entire input from the user.
     * @return the task id which user intends to mark as done.
     */
    private static int extractTaskId(String userInput) {
        int taskIdIndex = userInput.indexOf(" ") + 1;
        String doneTaskIdString = userInput.substring(taskIdIndex);
        try {
            return Integer.parseInt(doneTaskIdString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Extract the task description from the user input.
     *
     * @param taskInfo the second part of the user input.
     * @param delimiter the delimiter for the date in the user input.
     * @return the description of the task which user intends to add.
     * @throws DukeException exception is thrown when can't extract the task description.
     */
    private static String extractTaskDescription(String taskInfo, String delimiter) throws DukeException {
        int taskTimeIndex = taskInfo.indexOf(delimiter);
        try {
            return taskInfo.substring(0, taskTimeIndex).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * Extract the task date from the user input when the user intend to call `deadline` or `event`
     * @param taskInfo
     * @param delimiter
     * @return
     */
    private static String extractTaskDate(String taskInfo, String delimiter) {
        int taskTimeIndex = taskInfo.indexOf(delimiter) + delimiter.length();
        return taskInfo.substring(taskTimeIndex).trim();
    }

    private static LocalDate parseStringToDate(String dateString) throws DukeDateFormatException {
        try {
            LocalDate date = LocalDate.parse(dateString);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeDateFormatException();
        }
    }
}
