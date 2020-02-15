package duke;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.print.PrintHelper;
import duke.task.TaskManager;

import java.util.Scanner;

public class Duke {

    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String EMPTY_COMMAND = "";
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    // Handles all the tasks
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        PrintHelper.printWelcomeMessage();
        command = sc.nextLine();
        while (!command.equals(BYE_COMMAND)) {
            try {
                executeCommand(command);
            } catch (DukeException exception) {
                exception.printExceptionMessage();
            }
            command = sc.nextLine();
        }

        PrintHelper.printByeMessage();
    }

    // Executes the command entered by the user
    private static void executeCommand(String command) throws DukeException {
        String[] commandSplit = command.split(" ",2);
        String commandType = commandSplit[0];
        boolean isOneWordCommand = commandSplit.length == 1 || commandSplit[1].isBlank();
        try {
            switch (commandType) {
            case LIST_COMMAND:
                taskManager.listTasks(isOneWordCommand);
                break;
            case DONE_COMMAND:
                taskManager.markTaskAsDone(commandSplit);
                break;
            case TODO_COMMAND:
                taskManager.addToDoTask(commandSplit);
                break;
            case DEADLINE_COMMAND:
                taskManager.addDeadlineTask(commandSplit, isOneWordCommand);
                break;
            case DELETE_COMMAND:
                taskManager.deleteTask(commandSplit);
                break;
            case EVENT_COMMAND:
                taskManager.addEventTask(commandSplit, isOneWordCommand);
                break;
            case EMPTY_COMMAND:
                throw new DukeException(ExceptionType.EmptyCommand);
                // break statement can't be reached if added
            default:
                throw new DukeException(ExceptionType.InvalidCommand);
                // break statement can't be reached if added
            }
        } catch (DukeException dukeException) {
            dukeException.printExceptionMessage();
        }
        System.out.println();
    }

    // Prints that user entered an invalid Command
    private static void invalidCommand() {
        PrintHelper.printInvalidCommand();
    }

    // Prints that user entered an empty line
    private static void emptyCommand() {
        PrintHelper.printEmptyLineAlert();
    }

}
