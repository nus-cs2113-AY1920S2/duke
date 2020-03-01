package duke.parser;

import duke.commands.*;
import duke.exception.InvalidFormatException;
import duke.format.DateTime;
import duke.format.DateTimeFormat;
import duke.ui.UI;

import static duke.exception.ExceptionMessages.*;
import static duke.format.DateTimeFormat.stringToDate;

import static duke.format.DateTimeFormat.stringToDateTime;

/**
 * <h3>Parser</h3>
 * The <b>Parser</b> interprets the user input that is read by the <b>UI.java</b>.
 * The <b>Parser</b> then converts the input into a <b>Command</b> to be executed by the <b>LumiChat</b> program.
 * @see UI
 * @see Command
 */
public class Parser {
    private static final int MAX_INPUT_LENGTH = 50; // Maximum length of user input accepted
    private static final String DEADLINE_PREFIX = "/by";
    private static final String EVENT_PREFIX = "/at";

    /**
     * Parses the input string read by the <b>UI.java</b> and converts the string into a specific <b>Command</b>, which is
     * to be executed by the <b>LumiChat</b> program.
     * <p></p>
     * <b>Note</b>: The user input has to start with a certain keyword (i.e. <i>command word</i>), otherwise an
     * <i>Invalid Command Exception</i> will be thrown.
     *
     * @param input The user input read by the <b>UI.java</b>
     * @return The <b>corresponding</b> command to be executed
     * @throws EmptyInputException If user input is empty
     * @throws InputLengthExceededException If the length of the user input > {@value MAX_INPUT_LENGTH}
     * @throws InvalidCommandException If the user input cannot be recognised as any of the  <b>Command</b>
     * @see UI
     * @see Command
     */
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

        case HelpCommand.COMMAND_WORD:
            return createHelpCommand(parameters);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Creates the <b>Do Command</b> to be executed based on the parameters in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Do Command</b> to be executed
     * @see DoCommand
     * @see InvalidCommand
     */
    private Command createDoCommand(String parameters) {
        try {
            int index = extractIndex(parameters);
            return new DoCommand(index);
        } catch (MissingListNumberException e) {
            return new InvalidCommand(MISSING_LIST_NUMBER_MESSAGE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(INVALID_DONE_FORMAT_MESSAGE);
        } catch (NumberFormatException e) {
            // The list number is not an integer
            return new InvalidCommand(ILLEGAL_LIST_NUMBER_MESSAGE);
        }
    }

    /**
     * Creates the <b>Add To Do Command</b> to be executed based on the <code>parameters</code> in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Add To Do Command</b> to be executed
     * @see AddToDoCommand
     * @see InvalidCommand
     */
    private Command createToDoCommand(String parameters) {
        try {
            String task = extractDetails(parameters, null)[0];
            return new AddToDoCommand(task);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_TODO_DESCRIPTION_MESSAGE);
        }
    }

    /**
     * Creates the <b>Add Deadline Command</b> to be executed based on the <code>parameters</code> in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Add Deadline Command</b> to be executed
     * @see AddDeadlineCommand
     * @see InvalidCommand
     */
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

    /**
     * Creates the <b>Add Event Command</b> to be executed based on the <code>parameters</code> in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Add Event Command</b> to be executed
     * @see AddEventCommand
     * @see InvalidCommand
     */
    private Command createEventCommand(String parameters) {
        try {
            String[] taskDetails = extractDetails(parameters, EVENT_PREFIX);
            String task = taskDetails[0];
            DateTime dateTime = stringToDateTime(taskDetails[1]);
            return new AddEventCommand(task, dateTime);
        } catch (MissingParameterException e) {
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

    /**
     * Creates the <b>Delete Command</b> to be executed based on the <code>parameters</code> in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Delete Command</b> to be executed
     * @see DeleteCommand
     * @see InvalidCommand
     */
    private Command createDeleteCommand(String parameters) {
        try {
            int index = extractIndex(parameters);
            return new DeleteCommand(index);
        } catch (MissingListNumberException e) {
            return new InvalidCommand(MISSING_LIST_NUMBER_MESSAGE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(INVALID_DELETE_FORMAT_MESSAGE);
        } catch (NumberFormatException e) {
            // The list number is not an integer
            return new InvalidCommand(ILLEGAL_LIST_NUMBER_MESSAGE);
        }
    }

    /**
     * Creates the <b>Find Command</b> to be executed based on the <code>parameters</code> in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Find Command</b> to be executed
     * @see FindCommand
     * @see InvalidCommand
     */
    private Command createFindCommand(String parameters) {
        try {
            String searchWord = extractDetails(parameters, null)[0];
            return new FindCommand(searchWord);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_SEARCH_WORD_MESSAGE);
        }
    }

    /**
     * Creates the <b>Due Command</b> to be executed based on the <code>parameters</code> in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Due Command</b> to be executed
     * @see DueCommand
     * @see InvalidCommand
     */
    private Command createDueCommand(String parameters) {
        try {
            String dateFilter = extractDetails(parameters, null)[0];
            return createDueCommandFromDateFilter(dateFilter);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_DATE_FILTER_MESSAGE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(INVALID_DUE_FORMAT_MESSAGE);
        } catch (DateTimeFormat.InvalidDateException e) {
            return new InvalidCommand(INVALID_DATETIME_FORMAT_MESSAGE);
        }
    }

    /**
     * Interprets the <code>dateFilter</code> provided by the user for the <b>Due Command</b>.
     * <br> If valid, returns the corresponding <b>Due Command</b> to be executed.
     * <p></p>
     * The <i>date filter</i> comprise a specified <i>date</i> and an <u>optional</u> <i>time specifier</i>.
     *
     * @param dateFilter The date filter for the <b>Due Command</b>
     * @return The <b>Due Command</b> to be executed
     * @throws ExcessParameterException If there are more than 2 parameters
     * @throws DateTimeFormat.InvalidDateException If the <i>date</i> provided is invalid
     * @see DueCommand
     * @see DateTimeFormat
     */
    private Command createDueCommandFromDateFilter(String dateFilter)
            throws ExcessParameterException, DateTimeFormat.InvalidDateException {
        String[] dateFilterData = dateFilter.split("\\s+");

        if (dateFilterData.length == 1) {
            // Only date is provided
            return new DueCommand(stringToDate(dateFilterData[0]), null);
        } else if (dateFilterData.length == 2) {
            // Both date and time specifier is provided
            return new DueCommand(stringToDate(dateFilterData[1]), dateFilterData[0]);
        } else {
            throw new ExcessParameterException();
        }
    }

    /**
     * Creates the <b>Help Command</b> to be executed based on the <code>parameters</code> in the user input.
     * <br> Returns an <b>Invalid Command</b> if the parameters provided are invalid.
     *
     * @param parameters The parameters provided in the user input
     * @return The <b>Help Command</b> to be executed
     * @see DueCommand
     * @see InvalidCommand
     */
    private Command createHelpCommand(String parameters) {
        try {
            String helpWord = extractHelpWord(parameters);
            return new HelpCommand(helpWord);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MISSING_HELP_WORD_MESSAGE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(INVALID_HELP_FORMAT_MESSAGE);
        }
    }

    /**
     * Returns the <i>help word</i> that the user wants to query.
     *
     * @param parameters The parameters provided in the user input
     * @return The <i>help word</i> provided by the user
     * @throws MissingParameterException If no <i>help word</i> is given
     * @throws ExcessParameterException If more than one <i>help word</i> is given
     */
    private String extractHelpWord(String parameters) throws MissingParameterException, ExcessParameterException {
        if (parameters.isEmpty()) {
            throw new MissingParameterException();
        }

        // Checks if there is more than one parameter
        if (parameters.contains(" ")) {
            throw new ExcessParameterException();
        }

        return parameters;
    }

    /**
     * Returns the <i>index</i> of the <i>task</i>, based on the <i>list number</i> provided by the user.
     * <p></p>
     * <b>Note</b>: Only <b>1</b> list number is allowed to be provided.
     *
     * @param parameters The parameters provided in the user input
     * @return The <i>index</i> of the <i>task</i>
     * @throws MissingListNumberException If no <i>list number</i> is provided
     * @throws ExcessParameterException If there is more than one parameter
     */
    private int extractIndex(String parameters) throws MissingListNumberException, ExcessParameterException {
        if (parameters.isEmpty()) {
            throw new MissingListNumberException();
        }

        // Checks if there is more than one parameter
        if (parameters.contains(" ")) {
            throw new ExcessParameterException();
        }

        return Integer.parseInt(parameters) - 1; // 0-based indexing
    }

    /**
     * Interprets and returns the details of the <i>task</i> or <b>Command</b> provided by the user in a
     * <code>String Array</code>.
     * The <code>prefix</code> argument is optional and represents the <i>datetime</i> prefix for <b>Deadline</b>
     * tasks ({@value DEADLINE_PREFIX}) or <b>Event</b> tasks ({@value EVENT_PREFIX}).
     * <p></p>
     * There are 2 cases:
     * <ol>
     *     <li>
     *         <code>prefix</code> is <b><u>not</u></b> <code>NULL</code>
     *         <br> This is for <b>Add Deadline Command</b> and <b>Add Event Command</b>, where the <i>datetime</i>
     *         prefix is required to locate the datetime information. A <code>String Array</code> containing the task
     *         description and datetime detail is returned.
     *     </li>
     *     <li>
     *         <code>prefix</code> <b>is</b> <code>NULL</code>
     *         <br> This is for <b>To Do Command</b>, <b>Find Command</b> and <b>Due Command</b>.
     *         For the <b>Add To Do Command</b>, a <code>String Array</code> containing the task description is
     *         returned.  And for the <b>Find Command</b> and <b>Due Command</b>, a <code>String Array</code>
     *         containing the search word(s) is returned.
     *     </li>
     * </ol>
     *
     * @param parameters The parameters provided in the user input
     * @param prefix The prefix string for the <i>datetime</i> details of <b>Deadline</b> and <b>Event</b> tasks
     * @return An <code>String Array</code> with the required details of the <i>task</i> or <b>Command</b>
     * @throws MissingParameterException If no <i>task</i> details are provided
     * @see duke.task.Deadline
     * @see duke.task.Event
     */
    private String[] extractDetails(String parameters, String prefix) throws MissingParameterException {
        if (parameters.isEmpty()) {
            throw new MissingParameterException();
        }

        if (prefix == null) {
            return new String[]{parameters};
        } else {
            int endIndexOfTask = parameters.toLowerCase().indexOf(prefix);
            int indexOfDateTimeDetail = endIndexOfTask + prefix.length();

            String task = parameters.substring(0, endIndexOfTask).trim();
            String dateTimeDetail = parameters.substring(indexOfDateTimeDetail).trim();

            if (task.length() == 0 || dateTimeDetail.length() == 0) {
                throw new MissingParameterException();
            }

            return new String[]{task, dateTimeDetail};
        }
    }

    /** Signals that the user has provided an empty input. */
    public static class EmptyInputException extends InvalidFormatException {}

    /** Signals that the user has provided an input that is longer than {@value MAX_INPUT_LENGTH} characters. */
    public static class InputLengthExceededException extends InvalidFormatException {}

    /** Signals that the user has provided an unrecognised command */
    public static class InvalidCommandException extends InvalidFormatException {}

    /** Signals that the user has not provided a list number. */
    public static class MissingListNumberException extends InvalidFormatException {}

    /** Signals that the user has provided surplus parameters. */
    public static class ExcessParameterException extends InvalidFormatException {}

    /** Signals that the user has not provided sufficient parameters. */
    public static class MissingParameterException extends InvalidFormatException {}
}
