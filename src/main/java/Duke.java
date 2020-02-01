import java.util.Scanner;

public class Duke {

    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String EMPTY_COMMAND = "";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    // Handles all the tasks
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintHelper.printWelcomeMessage();
        String command;
        // Read command entered by user
        command = sc.nextLine();
        while (!command.equals(BYE_COMMAND)) {
            String[] commandSplit = command.split(" ",2);
            String commandType = commandSplit[0];
            boolean isOneWordCommand = commandSplit.length == 1 || commandSplit[1].isBlank();
            switch (commandType) {
            case LIST_COMMAND:
                if (isOneWordCommand){
                    // List the tasks stored
                    taskManager.listTasks();
                } else {
                    // Wrong format
                    PrintHelper.printInvalidCommand();
                }
                break;
            case EMPTY_COMMAND:
                // Prints that user entered an empty line
                PrintHelper.printEmptyLineAlert();
                break;
            case DONE_COMMAND:
                if (commandSplit.length == 1){
                    // Invalid done command
                    PrintHelper.printInvalidIntegerAlert();
                } else {
                    // Marks task mentioned by user as done
                    taskManager.markTask(commandSplit[1]);
                }
                break;
            case TODO_COMMAND:
                if (isOneWordCommand){
                    // Wrong format
                    PrintHelper.printInvalidToDoAlert();
                } else {
                    // Adds the Todo task specified by the user to the list
                    taskManager.addTask(TaskType.ToDo, commandSplit[1]);
                }
                break;
            case DEADLINE_COMMAND:
                if (isOneWordCommand){
                    // Wrong format
                    PrintHelper.printInvalidDeadlineAlert();
                } else {
                     // Adds the Deadline task specified by the user to the list
                    taskManager.addTask(TaskType.Deadline, commandSplit[1]);
                }
                break;
            case EVENT_COMMAND:
                if (isOneWordCommand){
                    // Wrong format
                    PrintHelper.printInvalidEventAlert();
                } else {
                    // Adds the Event task specified by the user to the list
                    taskManager.addTask(TaskType.Event, commandSplit[1]);
                }
                break;
            default:
                // Invalid Command
                PrintHelper.printInvalidCommand();
                break;
            }
            System.out.println();
            // Read next command entered by user
            command = sc.nextLine();
        }
        PrintHelper.printByeMessage();
    }
}
