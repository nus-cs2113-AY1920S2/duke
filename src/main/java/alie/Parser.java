package alie;

import alie.commands.AddDeadlineCommand;
import alie.commands.AddEventCommand;
import alie.commands.AddTodoCommand;
import alie.commands.Command;
import alie.commands.DeleteCommand;
import alie.commands.DoneCommand;
import alie.commands.ExitCommand;
import alie.commands.FindCommand;
import alie.commands.IncorrectCommand;
import alie.commands.ListCommand;
import alie.exceptions.InvalidCmdException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses all user inputs
 */
public class Parser {
    public static final DateTimeFormatter PRINT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM YYYY");
    /**
     * Parses user input and categorise them into a specific Command object using the first word
     * in param.
     * @param userCommandText String input provided by user.
     * @return Specific Command object based on first word in param.
     * @throws InvalidCmdException If any invalid command is detected while executing command.
     */
    public Command parseCommand(String userCommandText) throws InvalidCmdException {
        String[] splitCmds = userCommandText.split(" ", 2);
        String cmdType = splitCmds[0].toLowerCase();

        switch (cmdType) {
        case ExitCommand.COMMAND_KEYWORD:
            return new ExitCommand();
        case ListCommand.COMMAND_KEYWORD:
            return new ListCommand();
        case DoneCommand.COMMAND_KEYWORD:
            return new DoneCommand(splitCmds);
        case DeleteCommand.COMMAND_KEYWORD:
            return new DeleteCommand(splitCmds);
        case FindCommand.COMMAND_KEYWORD:
            return new FindCommand(splitCmds[1]);
        case AddTodoCommand.COMMAND_KEYWORD:
            return new AddTodoCommand(splitCmds);
        case AddDeadlineCommand.COMMAND_KEYWORD:
            return new AddDeadlineCommand(splitCmds);
        case AddEventCommand.COMMAND_KEYWORD:
            return new AddEventCommand(splitCmds);
        default:
            return new IncorrectCommand(userCommandText);
        }
    }

    /**
     * Splits details of Deadline and Event objects for easy differentiation based on given
     * divider and also check for error or invalid inputs given.
     * @param details The details for a deadline provided by user
     * @param divider Regex expression used to differentiate different details for the task
     *                object provided.
     * @param commandType COMMAND_KEYWORD of deadline or event to know which object is invoking
     *                    the function.
     * @return A string array with index 0 containing name of event and index 1 containing event
     *         time (that could be a deadline or event start date).
     * @throws InvalidCmdException If an unknown format is encountered.
     */
    public static String[] parseDeadlineOrEventDetails(String details, String divider,
                                                       String commandType)
            throws InvalidCmdException {
        String[] stringDetails = details.split(divider);
        if (stringDetails.length > 2) {
            throw new InvalidCmdException(InvalidCmdException.TOO_MANY_DATES_ERROR);
        } else if (stringDetails.length < 2) {
            throw new InvalidCmdException(String.format(
                    InvalidCmdException.DEADLINE_OR_EVENT_MISSING_DETAILS_ERROR, commandType));
        } else {
            return stringDetails;
        }
    }

    /**
     * Converting a string of specific date-time-format to another format if applicable, else
     * will simply print an warning message.
     * @param stringDate A string specifying the date or time.
     * @return LocalDate with pattern of MMM-dd-YYYY if stringDate matches pattern YYYY-MM-DD.
     *         Else unedited stringDate.
     */
    public static String parseDate(String stringDate) {
        try {
            LocalDate date = LocalDate.parse(stringDate);
            return date.format(PRINT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            Ui.printDateWarning();
            return stringDate;
        }
    }
}
