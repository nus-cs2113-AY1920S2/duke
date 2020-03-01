package duke.commands;

import duke.exception.InvalidFormatException;

import static duke.ui.Messages.HELP_MESSAGE;
import static duke.ui.HelpMessages.ADD_HELP;
import static duke.ui.HelpMessages.ADD_TO_DO_HELP;
import static duke.ui.HelpMessages.ADD_DEADLINE_HELP;
import static duke.ui.HelpMessages.ADD_EVENT_HELP;
import static duke.ui.HelpMessages.DO_HELP;
import static duke.ui.HelpMessages.LIST_HELP;
import static duke.ui.HelpMessages.DELETE_HELP;
import static duke.ui.HelpMessages.FIND_HELP;
import static duke.ui.HelpMessages.DUE_HELP;
import static duke.ui.HelpMessages.EXIT_HELP;
import static duke.ui.HelpMessages.DATETIME_HELP;
import static duke.ui.HelpMessages.DATE_HELP;
import static duke.ui.HelpMessages.TIME_HELP;
import static duke.ui.HelpMessages.TIME_SPECIFIER_HELP;
import static duke.exception.ExceptionMessages.INVALID_HELP_FORMAT_MESSAGE;

/**
 * <h3>Help Command (In progress)</h3>
 * A <b>Command</b> to show the usages of the various <b>Commands</b> to the user.
 * @see Command
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String FORMAT = "help";

    private final String helpWord;

    public HelpCommand(String helpWord) {
        this.helpWord = helpWord.toLowerCase();
    }

    private final String HELP_MESSAGE_DIVIDER = "-".repeat(120) + "\n\n";

    /**
     * Selects the corresponding <i>help message</i> to show the user from the <code>helpWord</code> given.
     *
     * @param helpWord The <i>help word</i> that the user wants to query
     * @return The corresponding <i>help message</i>
     * @throws UnknownHelpWordException If the <i>help word</i> is unrecognised
     */
    private String selectHelpMessage(String helpWord) throws UnknownHelpWordException {

        switch (helpWord) {

        case "add":
            return ADD_HELP;

        case "todo":
            return ADD_TO_DO_HELP;

        case "deadline":
            return ADD_DEADLINE_HELP;

        case "event":
            return ADD_EVENT_HELP;

        case "done":
            return DO_HELP;

        case "list":
            return LIST_HELP;

        case "delete":
            return DELETE_HELP;

        case "find":
            return FIND_HELP;

        case "due":
            return DUE_HELP;

        case "bye":
        case "exit":
            return EXIT_HELP;

        case "datetime":
            return DATETIME_HELP;

        case "date":
            return DATE_HELP;

        case "time":
            return TIME_HELP;

        case "timespecifier":
        case "timespec":
            return TIME_SPECIFIER_HELP;

        default:
            throw new UnknownHelpWordException();
        }
    }


    /**
     * Executes the <b>Help Command</b> to show the user the help guide on the <code>helpWord</code> given.
     *
     * @return The <b>Command Result</b> of the execution
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            return new CommandResult(HELP_MESSAGE + HELP_MESSAGE_DIVIDER + selectHelpMessage(helpWord));
        } catch (UnknownHelpWordException e) {
            return new CommandResult(INVALID_HELP_FORMAT_MESSAGE);
        }
    }

    /** Signals that the given help word is not recognised */
    public static class UnknownHelpWordException extends InvalidFormatException {}
}
