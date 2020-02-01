import java.util.Scanner;

public class Duke {

    public static final String DIVIDER = "    ____________________________________________________________";
    public static final int MAX_CAPACITY = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        runChatBot(MAX_CAPACITY);
    }

    private static void runChatBot(int MAX_CAPACITY) {
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[MAX_CAPACITY];
        taskManager(input, tasks);
    }

    private static void taskManager(Scanner input, Task[] Tasks) {
        int taskCounter = 0;
        while(true) {
            String userInput = getInput(input.nextLine());
            String[] userCommands = userInput.split(" ", 2);
            if (userCommands[0].equalsIgnoreCase("bye")) {
                printByeMessage();
                break;
            } else if (userCommands[0].equalsIgnoreCase("list")) {
                printList(Tasks, taskCounter);
            } else if (userCommands[0].equalsIgnoreCase("done")) {
                printDone(userCommands, Tasks);
            } else if(userCommands[0].equalsIgnoreCase("todo")) {
                addTodo(Tasks, taskCounter, userCommands);
                taskCounter ++;
            } else if(userCommands[0].equalsIgnoreCase("event")) {
                addEvent(Tasks, taskCounter, userCommands[1]);
                taskCounter++;
            } else if(userCommands[0].equalsIgnoreCase("deadline")) {
                addDeadline(Tasks, taskCounter, userCommands[1]);
                taskCounter++;
            } else {
                addTask(Tasks, taskCounter, userInput);
                taskCounter++;
            }
            System.out.println(DIVIDER);
        }
    }

    private static void addTask(Task[] Tasks, int taskCounter, String userInput) {
        String buffer = userInput;
        Tasks[taskCounter] = new Task(buffer.trim());
        System.out.println("    added: " + Tasks[taskCounter].description);
    }

    private static void addEvent(Task[] Tasks, int taskCounter, String userCommand) {
        try {
            String[] buffer = userCommand.split("/at", 2);
            Tasks[taskCounter] = new Event(buffer[0].trim(), buffer[1].trim());
            printTask(Tasks[taskCounter], taskCounter);
        } catch (Exception e){
            System.out.println("    Error: Insufficient detail");
        }
    }

    private static void addDeadline(Task[] Tasks, int taskCounter, String userCommand) {
        try {
            String[] buffer = userCommand.split("/by", 2);
            Tasks[taskCounter] = new DeadLine(buffer[0].trim(), buffer[1].trim());
            printTask(Tasks[taskCounter], taskCounter);
        } catch(Exception e) {
            System.out.println("    Error: Insufficient detail");
        }
    }

    private static void addTodo(Task[] Tasks, int taskCounter, String[] userCommands) {
        try {
            Tasks[taskCounter] = new Todo(userCommands[1]);
            printTask(Tasks[taskCounter], taskCounter);
        } catch(Exception e) {
            System.out.println("    Error: Insufficient detail");
        }
    }

    private static void printDone(String[] input, Task[] Tasks) {
        try {
            int num = Integer.parseInt(input[1]) - 1;
            Tasks[num].taskDone();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + Tasks[num]);
        }catch(NullPointerException e) {
            System.out.println("    Error: Given Index is out of bound");
        }catch(Exception e) {
            System.out.println("    Error: Insufficient detail");
        }
    }

    private static void printTask(Task task, int taskCounter) {
        System.out.println("    Got it! I've added this task:");
        System.out.println("       " + task);
        taskCounter ++;
        System.out.println("    Now you have " + taskCounter + " task(s) in the list.");
    }

    private static void printList(Task[] tasks, int taskCounter) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.println("    " + (i + 1) + "." + tasks[i]);
        }
    }

    private static void printByeMessage() {
        System.out.println("    Bye. Hope to see you soon!");
        System.out.println(DIVIDER);
    }

    private static String getInput(String next) {
        String input = next;
        System.out.println(DIVIDER);
        return input;
    }

    private static void printWelcomeMessage() {
        String logo = "    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                "    ░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░\n" +
                "    ░░░░░░░░▄▀░░░░░░░░░░░░▄░░░░░░░▀▄░░░░░░░\n" +
                "    ░░░░░░░░█░░▄░░░░▄░░░░░░░░░░░░░░█░░░░░░░\n" +
                "    ░░░░░░░░█░░░░░░░░░░░░▄█▄▄░░▄░░░█░▄▄▄░░░\n" +
                "    ░▄▄▄▄▄░░█░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██░░\n" +
                "    ░██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██░░\n" +
                "    ░░▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██░\n" +
                "    ░░░░▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██░\n" +
                "    ░░░░░░░▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██░\n" +
                "    ░░░░░░░▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀░░\n" +
                "    ░░░░░░█▀▀█████████▀▀▀▀████████████▀░░░░\n" +
                "    ░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀░░░░░░\n" +
                "    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n";
        System.out.println(DIVIDER);
        System.out.print(logo);
        System.out.println("    Hello Nyan Cat here!");
        System.out.println(DIVIDER);
    }
}

