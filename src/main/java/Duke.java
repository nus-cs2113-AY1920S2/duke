import java.util.Scanner;

public class Duke {

    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String EMPTY_COMMAND = "";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DEADLINE_SPECIFIER = "/by ";
    public static final String PERIOD_SPECIFIER = "/at ";

    // Handles all the tasks
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        PrintHelper.printWelcomeMessage();
        command = sc.nextLine();
        while (!command.equals(BYE_COMMAND)) {
            executeCommand(command);
            command = sc.nextLine();
        }
        PrintHelper.printByeMessage();
    }

    // Executes the command entered by the user
    private static void executeCommand(String command) {
        String[] commandSplit = command.split(" ",2);
        String commandType = commandSplit[0];
        boolean isOneWordCommand = commandSplit.length == 1 || commandSplit[1].isBlank();

        switch (commandType) {
        case LIST_COMMAND:
            listTasks(isOneWordCommand);
            break;
        case EMPTY_COMMAND:
            emptyCommand();
            break;
        case DONE_COMMAND:
            markTaskAsDone(commandSplit);
            break;
        case TODO_COMMAND:
            addToDoTask(commandSplit, isOneWordCommand);
            break;
        case DEADLINE_COMMAND:
            addDeadlineTask(commandSplit, isOneWordCommand);
            break;
        case EVENT_COMMAND:
            addEventTask(commandSplit, isOneWordCommand);
            break;
        default:
            invalidCommand();
            break;
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

    // Instructs the task manager to add the Event task specified by the user
    // to the list if the correct format is used
    private static void addEventTask(String[] commandSplit, boolean isOneWordCommand) {
        boolean isCorrectFormat = !isOneWordCommand && commandSplit[1].contains(PERIOD_SPECIFIER);
        if (isCorrectFormat) {
            taskManager.addTask(TaskType.Event, commandSplit[1]);
        } else {
            PrintHelper.printInvalidEventFormat();
        }
    }

    // Instructs the task manager to add the Deadline task specified by the user
    // to the list if the correct format is used
    private static void addDeadlineTask(String[] commandSplit, boolean isOneWordCommand) {
        boolean isCorrectFormat = !isOneWordCommand && commandSplit[1].contains(DEADLINE_SPECIFIER);
        if (isCorrectFormat) {
            taskManager.addTask(TaskType.Deadline, commandSplit[1]);
        } else {
            PrintHelper.printInvalidDeadlineFormat();
        }
    }

    // Instructs the task manager to add the ToDo task specified by the user
    //  to the list if the correct format is used
    private static void addToDoTask(String[] commandSplit, boolean isOneWordCommand) {
        boolean isCorrectFormat = !isOneWordCommand;
        if (isCorrectFormat) {
            taskManager.addTask(TaskType.ToDo, commandSplit[1]);
        } else {
            PrintHelper.printInvalidToDoFormat();
        }
    }

    // Instructs the task manager to mark the task done if the correct format is used
    private static void markTaskAsDone(String[] commandSplit) {
        boolean isValid = (commandSplit.length == 2);
        if (isValid) {
            taskManager.markTask(commandSplit[1]);
        } else {
            PrintHelper.printInvalidDoneFormat();
        }
    }

    // Instructs the task manager to list the tasks if the correct format is used
    private static void listTasks(boolean isOneWordCommand) {
        boolean isCorrectFormat = isOneWordCommand;
        if (isCorrectFormat) {
            taskManager.listTasks();
        } else {
            PrintHelper.printInvalidCommand();
        }
    }
}
