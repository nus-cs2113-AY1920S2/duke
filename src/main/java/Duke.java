import java.util.Scanner;

public class Duke {
    private static int counter = 0;
    private static Task[] listOfTasks =  new Task[100];

    public static void main(String[] args) {
        showWelcomeMessage();
        run();
    }

    private static void run() {
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            int dividerPosition = line.indexOf(" ");
            String command;
            String taskInformation = "";
            if (dividerPosition == -1) {
                command = line;
            } else {
                command = line.substring(0, dividerPosition);
                taskInformation = line.substring(dividerPosition+1, line.length());
            }
            if (command.equals("bye")) {
                exitFromApp();
                break;
            }
            switch (command) {
            case "list":
                listAllTasks();
                break;
            case "todo":
                storeTaskIntoList(taskInformation, "todo");
                break;
            case "deadline":
                storeTaskIntoList(taskInformation, "deadline");
                break;
            case "event":
                storeTaskIntoList(taskInformation, "event");
                break;
            case "done":
                markTaskAsDone(line);
                break;
            }
        }
    }

    private static void showWelcomeMessage() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println(logo);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public static void printLine() {
        for (int i = 0; i < 60; i += 1) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void exitFromApp() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void storeTaskIntoList(String line, String type) {
        int dividerPosition;
        String description;
        switch (type) {
        case "todo":
            listOfTasks[counter] = new Todo(line);
            counter += 1;
            break;
        case "deadline":
            dividerPosition = line.indexOf(" /by ");
            description = line.substring(0, dividerPosition);
            String dueDate = line.substring(dividerPosition+5, line.length());
            listOfTasks[counter] = new Deadline(description, dueDate);
            counter += 1;
            break;
        case "event":
            dividerPosition = line.indexOf(" /at ");
            description = line.substring(0, dividerPosition);
            String timePeriod = line.substring(dividerPosition+5, line.length());
            listOfTasks[counter] = new Event(description,timePeriod);
            counter += 1;
            break;
        }
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + listOfTasks[counter-1].getTypeIcon()
                +"[" + listOfTasks[counter-1].getStatusIcon() + "]"
                + " " + listOfTasks[counter-1].getDescription());
        System.out.println(" Now you have " + counter + " tasks in the list.");
        printLine();
    }

    public static void listAllTasks() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < counter; i += 1) {
            System.out.print(" ");
            System.out.print(i+1);
            System.out.print("." + listOfTasks[i].getTypeIcon() + "["
                    + listOfTasks[i].getStatusIcon() + "] ");
            System.out.println(listOfTasks[i].getDescription());
        }
        printLine();
    }

    public static void markTaskAsDone(String line) {
        int dividerPosition = line.indexOf("done");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition+5, line.length()));
        listOfTasks[taskNumber-1].markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print(" ");
        System.out.print("  [" + listOfTasks[taskNumber-1].getStatusIcon() + "] ");
        System.out.println(listOfTasks[taskNumber-1].getDescription());
        printLine();
    }
}
