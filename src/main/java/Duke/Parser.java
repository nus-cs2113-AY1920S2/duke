package Duke;

import Duke.Command.Command;
import Duke.Command.AddCommand;
import Duke.Command.DeleteCommand;
import Duke.Command.FindCommand;
import Duke.Command.DoneCommand;
import Duke.Command.ListCommand;
import Duke.Command.ExitCommand;
import Duke.UI.Messages;

/**
 * Parses the full command input from the user and determines which command should be executed.
 */
public class Parser {

    protected static String[] validCommands = {"todo", "deadline", "event", "done", "list", "delete", "bye", "find"};
    protected static final String LIST_COMMAND = "list";
    protected static final String DONE_COMMAND = "done";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String EXIT_COMMAND = "bye";
    protected static final String FIND_COMMAND = "find";

    /**
     * Gets the type of command to be executed from user input.
     *
     * @param fullCommand the full command input by the user.
     * @return String of name of command.
     */
    public static String getCommand(String fullCommand) {
        fullCommand.trim();
        String[] separatedCommand = fullCommand.split(" ", 2);
        return separatedCommand[0].toLowerCase();
    }

    /**
     * Checks if the command given by the user is a valid executable command supported by Duke.
     *
     * @param command name of command.
     * @return true if command valid, if not false.
     */
    public static boolean isValidCommand(String command) {
        boolean isValidCommand = false;
        for (String c : validCommands) {
            if (command.equals(c)) {
                isValidCommand = true;
            }
        }
        return isValidCommand;
    }

    /**
     * Gets the command to execute and determine if it is valid,
     * then if valid create a new command class of that command type.
     *
     * @param fullCommand the full command input by the user.
     * @return command object of command to be executed.
     * @throws DukeException if invalid command.
     */
    public static Command parse(String fullCommand) throws DukeException{
        String command = getCommand(fullCommand);

        if (!isValidCommand(command)) {
            throw new DukeException(Messages.MESSAGE_INVALID_COMMAND);
        }
        switch (command) {
        case LIST_COMMAND:
            return new ListCommand();
        case DONE_COMMAND:
            return new DoneCommand(command, fullCommand);
        case DELETE_COMMAND:
            return new DeleteCommand(command, fullCommand);
        case EXIT_COMMAND:
            return new ExitCommand();
        case FIND_COMMAND:
            return new FindCommand(fullCommand);
        default:
            return new AddCommand(command, fullCommand);
        }

    }

}
