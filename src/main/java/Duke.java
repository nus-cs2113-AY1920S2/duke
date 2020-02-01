import java.util.Scanner;

public class Duke {

    // Handles all the tasks
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintHelper.printWelcomeMessage();

        String command;
        // Read command entered by user
        command = sc.nextLine();
        while (!command.equals("bye")) {
            String[] commandSplit = command.split(" ",2);
            String commandType = commandSplit[0];
            switch (commandType) {
            case "list":
                // List the tasks stored
                taskManager.listTasks();
                break;
            case "":
                // Prints that user entered an empty line
                PrintHelper.printEmptyLineAlert();
                break;
            case "done":
                if (commandSplit.length == 1){
                    PrintHelper.printInvalidIntegerAlert();
                } else {
                    // Marks task mentioned by user as done
                    taskManager.markTask(commandSplit[1]);
                }
                break;
            case "todo":
                // Adds the task specified by the user to the list
                taskManager.addTask(TaskType.ToDo, commandSplit[1]);
                break;
            case "deadline":
                // Adds the task specified by the user to the list
                taskManager.addTask(TaskType.Deadline, commandSplit[1]);
                break;
            case "event":
                // Adds the task specified by the user to the list
                taskManager.addTask(TaskType.Event, commandSplit[1]);
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
