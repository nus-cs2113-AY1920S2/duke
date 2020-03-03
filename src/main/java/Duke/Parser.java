package Duke;

import Duke.Command.*;
import Duke.UI.Messages;

public class Parser {

    protected static String[] validCommands = {"todo", "deadline", "event", "done", "list", "delete", "bye", "find"};
    protected static final String LIST_COMMAND = "list";
    protected static final String DONE_COMMAND = "done";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String EXIT_COMMAND = "bye";
    protected static final String FIND_COMMAND = "find";

    public static String getCommand(String fullCommand) {
        fullCommand.trim();
        String[] separatedCommand = fullCommand.split(" ", 2);
        return separatedCommand[0].toLowerCase();
    }

    public static boolean isValidCommand(String command) {
        boolean isValidCommand = false;
        for (String c : validCommands) {
            if (command.equals(c)) {
                isValidCommand = true;
            }
        }
        return isValidCommand;
    }

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
