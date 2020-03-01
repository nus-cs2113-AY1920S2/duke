package parser;

import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.AddDeadlineCommand;
import commands.DeleteCommand;
import commands.Command;
import commands.DoneCommand;
import commands.ListCommand;
import commands.ExitCommand;
import commands.FindCommand;

import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains methods to parse user inputs into sensible commands.
 */
public class Parser {

    /**
     * Parses the user input into commands.
     * @param userInput
     * @return The command issued by the user.
     * @throws DukeException An exception is issued if the command issued by the user is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] arguments = userInput.split(" ",2);

        switch (arguments[0]){

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodo(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(arguments);

        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(arguments);

        case ListCommand.COMMAND_WORD:
            return prepareListTasks(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteTask(arguments);

        case DoneCommand.COMMAND_WORD:
            return prepareDoneTask(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFindTask(arguments);

        default:
            throw new DukeException("Invalid Command.");
        }
    }

    /**
     * Parses the user input into arguments for the AddTodo command.
     * @return AddTodo Command.
     */
    private static Command prepareAddTodo(String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        if (arguments[1] == null || arguments[1].isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        return new AddTodoCommand(arguments[1]);
    }

    /**
     * Parses the user input into arguments for the AddDeadline command.
     * @return AddDeadline Command.
     */
    private static Command prepareAddDeadline(String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("Missing description or deadline.");
        }

        String[] details = arguments[1].split("/by ");

        if (details[0] == null || details[0].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        if (details.length != 2) {
            throw new DukeException("Incorrect format.");
        }

        LocalDateTime dateTime = parseUserInputToLocalDateTime(details[1]);
        return new AddDeadlineCommand(details[0], dateTime);
    }

    /**
     * Parses the user input into arguments for the AddEvent command.
     * @return AddEvent Command.
     */
    private static Command prepareAddEvent(String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("Missing description or date and time.");
        }

        String[] details = arguments[1].split("/at ");

        if (details[0] == null || details[0].isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        if (details.length != 2) {
            throw new DukeException("Incorrect format.");
        }

        LocalDateTime dateTime = parseUserInputToLocalDateTime(details[1]);
        return new AddEventCommand(details[0], dateTime);
    }

    /**
     * Check if the user input is valid for the Add command.
     * @return List Command.
     */
    private static Command prepareListTasks(String[] arguments) throws DukeException {
        if (arguments.length > 1) {
            throw new DukeException("Incorrect format.");
        }

        return new ListCommand();
    }

    /**
     * Parses the user input into arguments for the Delete command.
     * @return Delete Command.
     */
    private static Command prepareDeleteTask(String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The task item has to be indicated.");
        }

        try {
            int itemNumber= Integer.parseInt(arguments[1]);
            return new DeleteCommand(itemNumber-1);
        } catch (NumberFormatException  e) {
            throw new DukeException("The task item has to be an integer.");
        }
    }

    /**
     * Parses the user input into arguments for the Done command.
     * @return Done Command.
     */
    private static Command prepareDoneTask(String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The task item has to be indicated.");
        }

        try {
            int itemNumber= Integer.parseInt(arguments[1]);
            return new DoneCommand(itemNumber-1);
        } catch (NumberFormatException  e) {
            throw new DukeException("The task item has to be an integer.");
        }
    }

    /** Parses user inputted date and time into LocalDateTime
     *
     * @param dateAndTimeString User inputted date and time string
     * @return LocalDateTime object
     * @throws DukeException If user inputted date and time are in the wrong format
     */
    public static LocalDateTime parseUserInputToLocalDateTime(String dateAndTimeString) throws DukeException {
        String[] dateAndTimeStrings = dateAndTimeString.split(" ");

        if (dateAndTimeStrings[1].length() != 4) {
            throw new DukeException("Incorrect time format.");
        } else if (dateAndTimeStrings.length != 2) {
            throw new DukeException("Incorrect date and time format.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateAndTimeString, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format.");
        }
    }

    /**
     * Parses the user input into arguments for the Find command.
     * @return Find Command.
     */
    private static Command prepareFindTask(String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The keyword has to be indicated.");
        }

        return new FindCommand(arguments[1]);
    }
}
