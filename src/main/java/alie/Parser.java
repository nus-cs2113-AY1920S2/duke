package alie;

import alie.commands.*;
import alie.exceptions.InvalidCmdException;

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
    public String[] parseDeadlineDetails(String details) throws InvalidCmdException {
        String[] stringDetails = details.split(DEADLINE_DETAIL_DIVIDER);
        if (stringDetails.length > 2) {
            throw new InvalidCmdException("Cannot have more than 2 dates provided.");
        } else if (stringDetails.length < 2) {
            throw new InvalidCmdException("Name or Date of Event cannot be missing!");
        } else {
            return stringDetails;
        }
    }

    public String[] parseEventDetails(String details) throws InvalidCmdException {
        String[] stringDetails = details.split(EVENT_DETAIL_DIVIDER);
        if (stringDetails.length > 2) {
            throw new InvalidCmdException("Cannot have more than 2 dates provided.");
        } else if (stringDetails.length < 2) {
            throw new InvalidCmdException("Name or Date of Event cannot be missing!");
        } else {
            return stringDetails;
        }
    }
}
