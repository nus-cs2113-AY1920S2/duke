import java.util.Scanner;

public class Alie {
    public static void main(String[] args) {
        printWelcomeMsg();

        TaskManager checkList = new TaskManager();
        Scanner userInput = new Scanner(System.in);
        boolean haveMoreCmds = true;

        while (haveMoreCmds) {
            printHeader();
            String cmd = getUserInput(userInput);
            haveMoreCmds = executeCmd(cmd, checkList);
        }
    }

    public static void printWelcomeMsg() {
        String logo =
                          "    /\\       |        |   |‾‾‾‾‾    \n"
                        + "   /  \\      |        |   |         \n"
                        + "  /____\\     |        |   |----     \n"
                        + " /      \\    |        |   |         \n"
                        + "/        \\ . |_____ . | . |_____ .  \n";
        System.out.println("Hello from\n" + logo);
        printHeader();
        System.out.println("What would you like to do?");
    }

    public static void printHeader() {
        System.out.print("ALIE> ");
    }

    private static String getUserInput(Scanner userInput) {
        return userInput.nextLine();
    }

    private static boolean executeCmd(String cmd, TaskManager thingsToDo) {
        if (cmd.equalsIgnoreCase("bye")) {
            //Exiting A.L.I.E
            System.out.println("Bye-bye!");
            return false;
        } else if (cmd.equalsIgnoreCase("list")) {
            //Print list with all tasks
            thingsToDo.printAllTasksAdded();
        } else if (cmd.toLowerCase().startsWith("done ")) {
            //Mark task as complete
            int indexOfTask = Integer.parseInt(cmd.substring(5));
            thingsToDo.markTaskCompleted(indexOfTask-1);
        } else if ((cmd.toLowerCase().startsWith("todo")) ||
                (cmd.toLowerCase().startsWith("deadline")) ||
                (cmd.toLowerCase().startsWith("event"))) {
            //Add task
            thingsToDo.addNewTask(cmd);
        } else {
            //Error
            System.out.println(TaskManager.INDENTATION +
                    "Invalid command. Please try again with valid command.");
        }
        return true;
    }
}
