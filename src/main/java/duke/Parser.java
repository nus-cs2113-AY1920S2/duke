package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input
 */
public class Parser {
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    public static final DateTimeFormatter PRINT_DATE_FORMAT = DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH':'mm");
    public static final DateTimeFormatter PRINT_TIME_FORMAT = DateTimeFormatter.ofPattern("HH':'mm");

    /**
     * Returns a Command object depending on the command input by the user.
     * @param fullCommand line input by the user, which represents a command
     * @return command input by the user, with the appropriate arguments set
     */
    public static Command parseCommand(String fullCommand) {
        String[] commandTokens = fullCommand.split(" ");
        String commandType = commandTokens[0].toLowerCase();

        switch (commandType) {
        case ByeCommand.BYE_COMMAND_NAME:
            // exit duke
            return new ByeCommand();
        case ListCommand.LIST_COMMAND_NAME:
            // show all tasks in the list
            return new ListCommand();
        case DeleteCommand.DELETE_COMMAND_NAME:
            // delete a task by its displayed list index
            return prepareDeleteCommand(commandTokens);
        case DoneCommand.DONE_COMMAND_NAME:
            // mark a task as done
            return prepareDoneCommand(commandTokens);
        case FindCommand.FIND_COMMAND_NAME:
            // find tasks with descriptions that match a query string
            return prepareFindCommand(fullCommand);
        case TodoCommand.TODO_COMMAND_NAME:
            // add to`do to tasks
            return prepareTodoCommand(fullCommand);
        case DeadlineCommand.DEADLINE_COMMAND_NAME:
            // add deadline to tasks
            return prepareDeadlineCommand(fullCommand);
        case EventCommand.EVENT_COMMAND_NAME:
            // add event to tasks
            return prepareEventCommand(fullCommand);
        default:
            // unknown command name
            return new IncorrectCommand(Ui.UNKNOWN_COMMAND_NAME_MESSAGE);
        }
    }

    public static LocalDateTime parseDate(String dateTimeString)
            throws DateTimeParseException, IndexOutOfBoundsException {
        // handle issue where there are multiple spaces between the date and the time
        String[] dateAndTime = dateTimeString.split("\\s+", 2);
        String formattedDateTimeString = dateAndTime[0] + " " + dateAndTime[1];
        return LocalDateTime.parse(formattedDateTimeString, INPUT_DATE_FORMAT);
    }

    private static Command prepareDeadlineCommand(String fullCommand) {
        // deadline command follows format: <taskType> <taskName> /by <date time>
        String[] deadlineInfo = null;
        try {
            deadlineInfo = fullCommand
                    .substring(DeadlineCommand.DEADLINE_COMMAND_NAME.length() + 1)
                    .trim()
                    .split(DeadlineCommand.COMMAND_DATE_TIME_DELIMITER, 2);
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.DEADLINE_INSUFFICIENT_ARGS_MESSAGE);
        }

        if (deadlineInfo.length != 2) {
            return new IncorrectCommand(Ui.DEADLINE_INSUFFICIENT_ARGS_MESSAGE);
        }

        String deadlineName = deadlineInfo[0].trim();
        LocalDateTime deadlineDate;
        try {
            deadlineDate = parseDate(deadlineInfo[1].trim());
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.INVALID_DATE_FORMAT_MESSAGE);
        }
        return new DeadlineCommand(deadlineName, deadlineDate);
    }

    private static Command prepareDeleteCommand(String[] commandTokens) {
        // delete command follows format: delete <deleteIndex>
        try {
            int deleteIndex = Integer.parseInt(commandTokens[1]) - 1;
            return new DeleteCommand(deleteIndex);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // deleteIndex is not specified
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_SPECIFIED_MESSAGE);
        } catch (NumberFormatException nfe) {
            // commandTokens[1] is unable to be parsed as an int
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_INTEGER_MESSAGE);
        }
    }

    private static Command prepareDoneCommand(String[] commandTokens) {
        // done command follows format: done <doneIndex>
        try {
            int doneIndex = Integer.parseInt(commandTokens[1]) - 1;
            return new DoneCommand(doneIndex);
        } catch (IndexOutOfBoundsException aioobe) {
            // taskIndex is not specified
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_SPECIFIED_MESSAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_INTEGER_MESSAGE);
        }
    }

    private static Command prepareEventCommand(String fullCommand) {
        // event command follows format: <taskType> <taskName> /at <date startTime - endTime>
        String[] eventInfo;
        try {
            eventInfo = fullCommand
                    .substring(EventCommand.EVENT_COMMAND_NAME.length() + 1)
                    .trim()
                    .split(EventCommand.COMMAND_DATE_TIME_DELIMITER, 2);
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.EVENT_INSUFFICIENT_ARGS_MESSAGE);
        }

        if (eventInfo.length != 2) { // insufficient arguments
            return new IncorrectCommand(Ui.EVENT_INSUFFICIENT_ARGS_MESSAGE);
        }
        String eventName = eventInfo[0].trim();
        String eventDate = eventInfo[1].trim();
        LocalDateTime startTime;
        LocalDateTime endTime;
        try {
            String[] eventDateAndTime = eventDate.split("\\s+", 2);
            String[] startAndEndTime = eventDateAndTime[1].split(EventCommand.COMMAND_START_END_TIME_DELIMITER, 2);
            startTime = parseDate(eventDateAndTime[0] + " " + startAndEndTime[0]);
            endTime = parseDate(eventDateAndTime[0] + " " + startAndEndTime[1]);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.INVALID_EVENT_DATE_FORMAT_MESSAGE);
        }

        if (startTime.isAfter(endTime)) {
            return new IncorrectCommand(Ui.INVALID_START_AND_END_TIME_MESSAGE);
        }
        return new EventCommand(eventName, startTime, endTime);
    }

    private static Command prepareFindCommand(String fullCommand) {
        // find command follows format: find <searchString>
        try {
            String searchString = fullCommand.substring(FindCommand.FIND_COMMAND_NAME.length() + 1).trim();
            return new FindCommand(searchString);
        } catch (StringIndexOutOfBoundsException sioobe) {
            return new IncorrectCommand(Ui.FIND_INSUFFICIENT_ARGS_MESSAGE);
        }
    }

    private static Command prepareTodoCommand(String fullCommand) {
        // to`do command follows format: <taskType> <taskName>
        try {
            String description = fullCommand.substring(TodoCommand.TODO_COMMAND_NAME.length() + 1).trim();
            return new TodoCommand(description);
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.TODO_INSUFFICIENT_ARGS_MESSAGE);
        }
    }
}
