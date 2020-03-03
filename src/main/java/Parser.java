import java.util.ArrayList;

public class Parser {

    protected static String[] validCommands = {"todo", "deadline", "event", "done", "list", "delete", "bye"};
    protected static final String LIST_COMMAND = "list";
    protected static final String DONE_COMMAND = "done";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String EXIT_COMMAND = "bye";

    public static String getCommand(String fullCommand) {
        fullCommand.trim();
        String[] separatedCommand = fullCommand.split(" ", 2);
        return separatedCommand[0].toLowerCase();
    }

    public static void checkValidCommand(String command) throws DukeException {
        boolean isValidCommand = false;
        for (String c : validCommands) {
            if (command.equals(c)) {
                isValidCommand = true;
            }
        }
        if (!isValidCommand) {
            throw new DukeException();
        }
    }

    public static Command parse(String fullCommand) {
        String command = getCommand(fullCommand);
        try {
            checkValidCommand(command);
        } catch (DukeException e) {
            Ui.out.println(Messages.MESSAGE_INVALID_COMMAND);
        } finally {
            switch (command) {
                case LIST_COMMAND:
                    return new ListCommand();
                case DONE_COMMAND:
                    return new DoneCommand(command, fullCommand);
                case DELETE_COMMAND:
                    return new DeleteCommand(command, fullCommand);
                case EXIT_COMMAND:
                    return new ExitCommand();
                default:
                    return new AddCommand(command, fullCommand);
            }
        }
    }

}
