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
import duke.exception.DukeException;

import static duke.util.Constants.DEADLINE_COMMAND;
import static duke.util.Constants.DEADLINE_COMMAND_SHORTCUT;
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
import static duke.util.Constants.TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE;
import static duke.util.Constants.TODO_COMMAND;
import static duke.util.Constants.TODO_COMMAND_SHORTCUT;
import static duke.util.Constants.UNKNOWN_COMMAND_RESPONSE;

public class Parser {
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
                String taskDate = extractTaskDate(afterCommand, DEADLINE_TIME_DELIMITER);
                return new DeadlineCommand(taskDescriptions, taskDate);
            } catch (DukeException e) {
                throw new DukeException(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
            }
        case EVENT_COMMAND:
        case EVENT_COMMAND_SHORTCUT:
            try {
                String taskDescriptions = extractTaskDescription(afterCommand, EVENT_TIME_DELIMITER);
                String taskDate = extractTaskDate(afterCommand, EVENT_TIME_DELIMITER);
                return new EventCommand(taskDescriptions, taskDate);
            } catch (DukeException e) {
                throw new DukeException(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
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

    private static String[] splitCommand(String userInput) {
        String commandWord = extractCommandWord(userInput);
        String afterCommand = userInput.substring(commandWord.length()).trim();
        String[] commands = new String[]{commandWord, afterCommand};
        return commands;
    }

    private static String extractCommandWord(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        if (spaceIndex == -1) {
            return userInput;
        }
        return userInput.substring(0, spaceIndex);
    }

    private static int extractTaskId(String userInput) {
        int taskIdIndex = userInput.indexOf(" ") + 1;
        String doneTaskIdString = userInput.substring(taskIdIndex);
        try {
            return Integer.parseInt(doneTaskIdString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String extractTaskDescription(String taskInfo, String delimiter) throws DukeException {
        int taskTimeIndex = taskInfo.indexOf(delimiter);
        try {
            return taskInfo.substring(0, taskTimeIndex).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    private static String extractTaskDate(String taskInfo, String delimiter) {
        int taskTimeIndex = taskInfo.indexOf(delimiter) + delimiter.length();
        return taskInfo.substring(taskTimeIndex).trim();
    }
}
