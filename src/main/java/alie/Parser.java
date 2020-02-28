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
    protected static final String DEADLINE_DETAIL_DIVIDER = " /by ";
    protected static final String EVENT_DETAIL_DIVIDER = " /at ";

    /**
     * Parses user input and categorise them into a specific command using the first word in param.
     * @param userCommandText String input provided by user.
     * @return Specific Command Object based on first word in param.
     * @throws InvalidCmdException If any invalid command is detected.
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
            return new DoneCommand(splitCmds[1]);
        case DeleteCommand.COMMAND_KEYWORD:
            return new DeleteCommand(splitCmds[1]);
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
     * Splits details of Deadline for easy usage and also check for error or invalid command
     * in input
     * @param details The details for a deadline provided by user
     * @return A string array with each relevant index containing specific information
     * @throws InvalidCmdException If command provided is detected to be invalid.
     */
    public static String[] parseDeadlineOrEventDetails(String details, String divider)
            throws InvalidCmdException {
        String[] stringDetails = details.split(divider);
        if (stringDetails.length > 2) {
            throw new InvalidCmdException("Cannot have more than 2 dates provided.");
        } else if (stringDetails.length < 2) {
            throw new InvalidCmdException(String.format("Name or Date %1$s cannot be missing!"
                    , eventOrDetails(divider)));
        } else {
            return stringDetails;
        }
    }

    private static String eventOrDetails(String divider) {
        if (divider.equals(DEADLINE_DETAIL_DIVIDER)) {
            return "of Deadline";
        } else if (divider.equals(EVENT_DETAIL_DIVIDER)) {
            return "of Event";
        } else {
            return "";
        }
    }

    public static String parseDate(String stringDate) {
        try {
            LocalDate date = LocalDate.parse(stringDate);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            Ui.printTimeWarning();
            return stringDate;
        }
    }
}

