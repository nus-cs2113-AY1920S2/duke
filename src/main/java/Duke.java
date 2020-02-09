import java.util.Scanner;

public class Duke {
    private static int counter = 0;
    public static final int MAXIMUM_TASKS = 100;
    private static Task[] listOfTasks =  new Task[MAXIMUM_TASKS];
    private static final String logo = "  ____        _        \n"
                                 + " |  _ \\ _   _| | _____ \n"
                                 + " | | | | | | | |/ / _ \\\n"
                                 + " | |_| | |_| |   <  __/\n"
                                 + " |____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        showWelcomeMessage();
        run();
    }

    private static void showWelcomeMessage() {
        printLine();
        System.out.println(logo);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printLine();
    }

    private static void printLine() {
        for (int i = 0; i < 60; i += 1) {
            System.out.print("_");
        }
        System.out.println();
    }

    private static String getInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    private static String getCommand(String line) {
        int dividerPosition = line.indexOf(" ");
        if (dividerPosition != -1) {
            return line.substring(0, dividerPosition);
        } else {
            return line;
        }
    }

    public static void exitFromApp() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
        System.exit(0);
    }

    public static String getTaskInformation(String line) {
        int dividerPosition = line.indexOf(" ");
        return line.substring(dividerPosition+1);
    }

    private static void run() {
        while (true) {
            String line = getInput();
            String command = getCommand(line);
            switch (command) {
            case "bye":
                exitFromApp();
                break;
            case "list":
                listAllTasks();
                break;
            case "done":
                markTaskAsDone(line);
                break;
            default:
                String taskInformation = getTaskInformation(line);
                storeTaskIntoList(taskInformation, command);
                break;
            }
        }
    }

    public static void storeTaskIntoList(String taskInformation, String separator, String command) {
        int dividerPosition = taskInformation.indexOf(separator);
        String description = taskInformation.substring(0, dividerPosition);
        String dateAndTime = taskInformation.substring(dividerPosition+5);
        if (command.equals("deadline")) {
            listOfTasks[counter] = new Deadline(description, dateAndTime);
        } else if (command.equals("event")) {
            listOfTasks[counter] = new Event(description, dateAndTime);
        }

    }

    public static void storeTaskIntoList(String taskInformation, String command) {
        switch (command) {
        case "todo":
            listOfTasks[counter] = new Todo(taskInformation);
            break;
        case "deadline":
            storeTaskIntoList(taskInformation, " /by ", command);
            break;
        case "event":
            storeTaskIntoList(taskInformation, " /at ", command);
            break;
        }
        counter += 1;
        showStoredTask();
    }

    private static void showStoredTask() {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + listOfTasks[counter-1].getTypeIcon()
                +"[" + listOfTasks[counter-1].getStatusIcon() + "]"
                + " " + listOfTasks[counter-1].showFullDescription());
        System.out.println(" Now you have " + counter + " tasks in the list.");
        printLine();
    }

    public static void listAllTasks() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < counter; i += 1) {
            System.out.print(" " + (i+1) + "." + listOfTasks[i].getTypeIcon() + "["
                    + listOfTasks[i].getStatusIcon() + "] ");
            System.out.println(listOfTasks[i].showFullDescription());
        }
        printLine();
    }

    public static void markTaskAsDone(String line) {
        int dividerPosition = line.indexOf("done");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition+5));
        listOfTasks[taskNumber-1].markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print("   [" + listOfTasks[taskNumber-1].getStatusIcon() + "] ");
        System.out.println(listOfTasks[taskNumber-1].showFullDescription());
        printLine();
    }
}
