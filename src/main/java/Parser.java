import java.util.ArrayList;

public class Parser {

    protected String fullCommand;
    protected String[] validCommands = {"todo", "deadline", "event", "done", "list", "delete", "bye"};
    protected static final String LIST_COMMAND = "list";
    protected static final String DONE_COMMAND = "done";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String EXIT_COMMAND = "bye";

    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String getCommand () {
        String[] separatedCommand = fullCommand.split(" ", 2);
        return separatedCommand[0].toLowerCase();
    }

    public void checkValidCommand(String command) throws DukeException {
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

    public Command parse() {
        String command = getCommand();
        try {
            checkValidCommand(command);
        } catch (DukeException e) {
            Ui.showInvalidCommandError();
        }
        switch (command) {
            case LIST_COMMAND:
                return new ListCommand();
            case DONE_COMMAND:
                return new DoneCommand();
            case DELETE_COMMAND:
                return new DeleteCommand();
            case EXIT_COMMAND:
                return new ExitCommand();
            default:
                return new AddCommand(command);
        }
    }



    public static void determineTask(String command, String taskDescription, ArrayList<Task> taskList) {
        String action, date;
        try {
            checkMissingDescription(command, taskDescription, taskList);
        } catch (DukeException e) {
            printErrorMessage(command);
            return;
        } catch (NumberFormatException e) { //if parse invalid string into Integer.parseInt()
            printErrorMessage(command);
            return;
        } catch (ArrayIndexOutOfBoundsException e) { //if arr[1] doesn't exist and no word after command
            printErrorMessage(command);
            return;
        }

    }




}
