package duke.parser;

import duke.commands.Command;
import duke.commands.DoCommand;
import duke.commands.AddToDoCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.InvalidCommand;

import duke.exception.EmptyInputException;
import duke.exception.InputLengthExceededException;
import duke.exception.InvalidActionException;
import duke.exception.InvalidFormatException;

import static duke.exception.ExceptionMessage.ILLEGAL_LIST_NUMBER_MESSAGE;
import static duke.exception.ExceptionMessage.MISSING_LIST_NUMBER_MESSAGE;
import static duke.exception.ExceptionMessage.INVALID_DONE_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessage.MISSING_TODO_DESCRIPTION_MESSAGE;
import static duke.exception.ExceptionMessage.MISSING_DEADLINE_INFORMATION_MESSAGE;
import static duke.exception.ExceptionMessage.INVALID_DEADLINE_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessage.MISSING_EVENT_INFORMATION_MESSAGE;
import static duke.exception.ExceptionMessage.INVALID_EVENT_FORMAT_MESSAGE;
import static duke.exception.ExceptionMessage.INVALID_DELETE_FORMAT_MESSAGE;

public class Parser {
    final int MAX_INPUT_LENGTH = 50;
    final String DEADLINE_PREFIX = "/by";
    final String EVENT_PREFIX = "/at";

    public Command parseInput(String input) throws EmptyInputException, InputLengthExceededException, InvalidActionException {
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

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new InvalidActionException();
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
            String task = extractTaskDetails(parameters, null)[0];
            return new AddToDoCommand(task);
        } catch (MissingTaskDetailException e) {
            return new InvalidCommand(MISSING_TODO_DESCRIPTION_MESSAGE);
        }
    }

    private Command createDeadlineCommand(String parameters) {
        try {
            String[] taskDetails = extractTaskDetails(parameters, DEADLINE_PREFIX);
            String task = taskDetails[0];
            String deadline = taskDetails[1];
            return new AddDeadlineCommand(task, deadline);
        } catch (MissingTaskDetailException e) {
            return new InvalidCommand(MISSING_DEADLINE_INFORMATION_MESSAGE);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(INVALID_DEADLINE_FORMAT_MESSAGE);
        }
    }

    private Command createEventCommand(String parameters) {
        try {
            String[] taskDetails = extractTaskDetails(parameters, EVENT_PREFIX);
            String task = taskDetails[0];
            String duration = taskDetails[1];
            return new AddDeadlineCommand(task, duration);
        } catch (MissingTaskDetailException e) {
            return new InvalidCommand(MISSING_EVENT_INFORMATION_MESSAGE);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(INVALID_EVENT_FORMAT_MESSAGE);
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

    private int extractIndex(String parameters) throws MissingListNumberException, ExcessParameterException {
        if (parameters.isEmpty()) {
            throw new MissingListNumberException();
        }

        if (parameters.contains(" ")) {
            throw new ExcessParameterException();
        }

        return Integer.parseInt(parameters) - 1; // 0-based indexing
    }

    private String[] extractTaskDetails(String parameters, String prefix) throws MissingTaskDetailException {
        if (parameters.isEmpty()) {
            throw new MissingTaskDetailException();
        }

        if (prefix == null) {
            return new String[]{parameters};
        } else {
            int endIndexOfTask = parameters.indexOf(prefix);
            int indexOfAdditionalDetail = endIndexOfTask + prefix.length(); // Index of deadline / duration info

            String task = parameters.substring(0, endIndexOfTask).trim();
            String additionalDetail = parameters.substring(indexOfAdditionalDetail).trim(); // Deadline / duration info

            if (task.length() == 0 || additionalDetail.length() == 0) {
                throw new MissingTaskDetailException();
            }

            return new String[]{task, additionalDetail};
        }
    }

    public static class MissingListNumberException extends InvalidFormatException {}

    public static class ExcessParameterException extends InvalidFormatException {}

    public static class MissingTaskDetailException extends InvalidFormatException {}
}
