package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoCommand;
import duke.commands.DueCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.exception.InvalidFormatException;
import duke.format.DateTime;
import duke.format.DateTimeFormat;

import static duke.exception.ExceptionMessages.ILLEGAL_LIST_NUMBER_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_DATETIME_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_DATE_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_DEADLINE_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_DELETE_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_DONE_FORMAT_MESSAGE;
<<<<<<< HEAD
import static duke.exception.ExceptionMessages.INVALID_DUE_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_EVENT_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_TIME_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_DATE_FILTER_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_DEADLINE_INFORMATION_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_EVENT_INFORMATION_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_LIST_NUMBER_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_SEARCH_WORD_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_TODO_DESCRIPTION_MESSAGE;
import static duke.format.DateTimeFormat.stringToDate;
=======
import static duke.exception.ExceptionMessages.INVALID_EVENT_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_TIME_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_DEADLINE_INFORMATION_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_EVENT_INFORMATION_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_LIST_NUMBER_MESSAGE;
import static duke.exception.ExceptionMessages.MISSING_TODO_DESCRIPTION_MESSAGE;
>>>>>>> master
import static duke.format.DateTimeFormat.stringToDateTime;

public class Parser {
    final int MAX_INPUT_LENGTH = 50;
    final String DEADLINE_PREFIX = "/by";
    final String EVENT_PREFIX = "/at";

    public Command parseInput(String input)
            throws EmptyInputException, InputLengthExceededException, InvalidCommandException {
        if (input.isEmpty()) {
            throw new EmptyInputException();
        }
        if (input.length() > MAX_INPUT_LENGTH) {
            throw new InputLengthExceededException();
        }

        // Splits user input into command word and rest of parameters (if any)
        String[] separatedInput = input.split(" ", 2);

        String commandWord = separatedInput[0].toLowerCase();
        String parameters = (separatedInput.length == 2) ? separatedInput[1].trim() : "";

        switch (commandWord) {

        case DoCommand.COMMAND_WORD:
            return createDoCommand(parameters);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case AddToDoCommand.COMMAND_WORD:
            return createToDoCommand(parameters);

        case AddDeadlineCommand.COMMAND_WORD:
            return createDeadlineCommand(parameters);

        case AddEventCommand.COMMAND_WORD:
            return createEventCommand(parameters);

        case DeleteCommand.COMMAND_WORD:
            return createDeleteCommand(parameters);

        case FindCommand.COMMAND_WORD:
            return createFindCommand(parameters);

        case DueCommand.COMMAND_WORD:
            return createDueCommand(parameters);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new InvalidCommandException();
        }
    }

    private Command createDoCommand(String parameters) {
        try {
            int index = extractIndex(parameters);
            return new DoCommand(index);
        } catch (MissingListNumberException e) {
            return new InvalidCommand(MISSING_LIST_NUMBER_MESSAGE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(INVALID_DONE_FORMAT_MESSAGE);
        } catch (NumberFormatException e) {
            return new InvalidCommand(ILLEGAL_LIST_NUMBER_MESSAGE);
        }
    }

    private Command createToDoCommand(String parameters) {
        try {
            String task = extractDetails(parameters, null)[0];
            return new AddToDoCommand(task);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_TODO_DESCRIPTION_MESSAGE);
        }
    }

    private Command createDeadlineCommand(String parameters) {
        try {
            String[] taskDetails = extractDetails(parameters, DEADLINE_PREFIX);
            String task = taskDetails[0];
            DateTime deadline = stringToDateTime(taskDetails[1]);
            return new AddDeadlineCommand(task, deadline);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_DEADLINE_INFORMATION_MESSAGE);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(INVALID_DEADLINE_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            return new InvalidCommand(INVALID_DATETIME_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidDateException e) {
            return new InvalidCommand(INVALID_DATE_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidTimeException e) {
            return new InvalidCommand(INVALID_TIME_FORMAT_MESSAGE);
        }
    }

    private Command createEventCommand(String parameters) {
        try {
            String[] taskDetails = extractDetails(parameters, EVENT_PREFIX);
            String task = taskDetails[0];
            DateTime dateTime = stringToDateTime(taskDetails[1]);
            return new AddEventCommand(task, dateTime);
<<<<<<< HEAD
        } catch (MissingParameterException e) {
=======
        } catch (MissingTaskDetailException e) {
>>>>>>> master
            return new InvalidCommand(MISSING_EVENT_INFORMATION_MESSAGE);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(INVALID_EVENT_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            return new InvalidCommand(INVALID_DATETIME_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidDateException e) {
            return new InvalidCommand(INVALID_DATE_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidTimeException e) {
            return new InvalidCommand(INVALID_TIME_FORMAT_MESSAGE);
        }
    }

    private Command createDeleteCommand(String parameters) {
        try {
            int index = extractIndex(parameters);
            return new DeleteCommand(index);
        } catch (MissingListNumberException e) {
            return new InvalidCommand(MISSING_LIST_NUMBER_MESSAGE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(INVALID_DELETE_FORMAT_MESSAGE);
        } catch (NumberFormatException e) {
            return new InvalidCommand(ILLEGAL_LIST_NUMBER_MESSAGE);
        }
    }

    private Command createFindCommand(String parameters) {
        try {
            String searchWord = extractDetails(parameters, null)[0];
            return new FindCommand(searchWord);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_SEARCH_WORD_MESSAGE);
        }
    }

    private Command createDueCommand(String parameters) {
        try {
            String dateFilter = extractDetails(parameters, null)[0];
            return processDateFilter(dateFilter);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_DATE_FILTER_MESSAGE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(INVALID_DUE_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidDateException e) {
            return new InvalidCommand(INVALID_DATETIME_FORMAT_MESSAGE);
        }
    }

    private Command processDateFilter(String dateFilter) throws ExcessParameterException, DateTimeFormat.InvalidDateException {
        String[] dateFilterData = dateFilter.split("\\s+");

        if (dateFilterData.length > 2) {
            throw new ExcessParameterException();
        }

        if (dateFilterData.length == 2) {
            return new DueCommand(stringToDate(dateFilterData[1]), dateFilterData[0]);
        } else {
            return new DueCommand(stringToDate(dateFilterData[0]));
        }
    }

    private int extractIndex(String parameters) throws MissingListNumberException, ExcessParameterException {
        if (parameters.isEmpty()) {
            throw new MissingListNumberException();
        }

        if (parameters.contains(" ")) {
            throw new ExcessParameterException();
        }

        return Integer.parseInt(parameters) - 1; // 0-based indexing
    }

    private String[] extractDetails(String parameters, String prefix) throws MissingParameterException {
        if (parameters.isEmpty()) {
            throw new MissingParameterException();
        }

        if (prefix == null) {
            return new String[]{parameters};
        } else {
            int endIndexOfTask = parameters.toLowerCase().indexOf(prefix);
            int indexOfDateTimeDetail = endIndexOfTask + prefix.length(); // Index of deadline / duration info

            String task = parameters.substring(0, endIndexOfTask).trim();
            String additionalDetail = parameters.substring(indexOfDateTimeDetail).trim(); // Deadline / duration info

            if (task.length() == 0 || additionalDetail.length() == 0) {
                throw new MissingParameterException();
            }

            return new String[]{task, additionalDetail};
        }
    }

    public static class EmptyInputException extends InvalidFormatException {}

    public static class InputLengthExceededException extends InvalidFormatException {}

    public static class InvalidCommandException extends InvalidFormatException {}

    public static class MissingListNumberException extends InvalidFormatException {}

    public static class ExcessParameterException extends InvalidFormatException {}

    public static class MissingParameterException extends InvalidFormatException {}
}
