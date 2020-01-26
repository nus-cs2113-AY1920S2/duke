import java.util.Scanner;

public class Alie {
    public static void main(String[] args) {
        welcomeMsg();

        TaskManager thingsToDo = new TaskManager();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String cmd = userInput.nextLine();
            header();
            if (cmd.equalsIgnoreCase("bye")) {
                //Exiting A.L.I.E
                System.out.println("Bye-bye!");
                break;
            } else if (cmd.equalsIgnoreCase("list")) {
                //Print list
                thingsToDo.printTasksAdded();
            } else if (cmd.toLowerCase().contains("done")) {
                //Mark completed task
                int indexOfTask = Integer.parseInt(cmd.substring(5));
                thingsToDo.markTaskCompleted(indexOfTask-1);
            } else {
                //Add task
                thingsToDo.addNewTask(cmd);
            }
        }
    }

    public static void welcomeMsg() {
        String logo =
                          "    /\\       |        |   |‾‾‾‾‾    \n"
                        + "   /  \\      |        |   |         \n"
                        + "  /____\\     |        |   |----     \n"
                        + " /      \\    |        |   |         \n"
                        + "/        \\ . |_____ . | . |_____ .  \n";
        System.out.println("Hello from\n" + logo);
        header();
        System.out.println("What would you like to do?");
    }

    public static void header() {
        System.out.print("ALIE> ");
    }
}