import java.util.Scanner;

public class Duke {

    public static final int MAXIMUM_TASKS = 100;
    private static final String DIVIDER = "===================================================";

    public static void main(String[] args) {

        printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        printUserGreeting(name);
        manageTasks(sc, name);
    }

    private static void manageTasks(Scanner sc, String name) {
        Task[] tasks = new Task[MAXIMUM_TASKS];
        int taskNumber = 0;

        while (true) {
            String input = sc.nextLine();
            String[] parseInput = input.split(" ",2);
            String command = parseInput[0];

            if (command.equalsIgnoreCase("done")) {
                printDoneTasks(tasks, parseInput[1]);
            } else if (command.equalsIgnoreCase("list")) {
                printList(tasks, taskNumber);
            } else if (command.equalsIgnoreCase("todo")) {
                taskNumber++;
                addToDoTask(tasks, taskNumber, parseInput);
            } else if (command.equalsIgnoreCase("bye")) {
                printByeMessage(name);
                break;
            } else if (command.equalsIgnoreCase("event")) {
                taskNumber++;
                addEvent(tasks, taskNumber, parseInput[1]);
            } else if (command.equalsIgnoreCase("deadline")){
                taskNumber++;
                addDeadline(tasks, taskNumber, parseInput[1]);
            } else {
                taskNumber ++;
                System.out.println(String.format("%50s", "added: " + input));
                tasks[taskNumber] = new Task(input);
                System.out.println(DIVIDER);
            }
        }
    }

    private static void printList(Task[] tasks, int taskNumber) {
        System.out.println(String.format("%50s", "Here are the tasks in your list:"));
        for (int i = 1; i <= taskNumber; i++) {
            System.out.println(String.format("%50s", i + ". " + tasks[i]));
        }
        System.out.println(DIVIDER);
    }

    private static void addDeadline(Task[] tasks, int taskNumber, String s) {
        System.out.println(String.format("%50s", "Got it. I've added this deadline:"));
        String[] event = s.split("/by",2);
        String time = event[1];//throw error if no time
        tasks[taskNumber] = new Deadline(event[0], time);
        System.out.println(String.format("%50s", tasks[taskNumber]));
        System.out.println(String.format("\n%50s", taskNumber+" tasks in the list (╥_╥)"));
        System.out.println(DIVIDER);
    }

    private static void addEvent(Task[] tasks, int taskNumber, String s) {
        System.out.println(String.format("%50s", "Got it. I've added this event:"));
        String[] event = s.split("/at",2);
        String time = event[1];
        tasks[taskNumber] = new Event(event[0], time);
        System.out.println(String.format("%50s", tasks[taskNumber]));
        System.out.println(String.format("\n%50s", taskNumber + " tasks in the list (╥_╥)"));
        System.out.println(DIVIDER);
    }

    private static void addToDoTask(Task[] tasks, int taskNumber, String[] parseInput) {
        System.out.println(String.format("%50s", "Got it. I've added this task:"));
        tasks[taskNumber] = new ToDo(parseInput[1]);
        System.out.println(String.format("%50s", tasks[taskNumber]));
        System.out.println(String.format("\n%50s", taskNumber + " tasks in the list (╥_╥)"));
        System.out.println(DIVIDER);
    }

    private static void printDoneTasks(Task[] tasks, String s) {
        try {
            int doneTask = Integer.parseInt(s);
            tasks[doneTask].updateTask();
            System.out.println(String.format("%50s", "Nice! I've marked this task as done:"));
            System.out.println(String.format("%50s", tasks[doneTask]));
            System.out.println(DIVIDER);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Task not included in the list, please try again.");
        } catch (Exception e) {
            System.out.println("Unknown error occurred, please try again.");
        }
    }

    private static void printByeMessage(String name) {
        System.out.println(String.format("%50s","Bye, "+ name + ". Hope to see you again soon!"));
        System.out.println(DIVIDER);
    }

    private static void printUserGreeting(String name) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%50s","Hello " + name + ", Anything I can help you with? "));
        System.out.println(DIVIDER);
    }

    private static void printWelcomeMessage() {
        String logo = " /$$   /$$                     /$$          \n"
                +     "| $$  /$$/                    |__/          \n"
                +     "| $$ /$$/   /$$$$$$   /$$$$$$$ /$$ /$$$$$$$ \n"
                +     "| $$$$$/   /$$__  $$ /$$_____/| $$| $$__  $$ \n"
                +     "| $$  $$  | $$$$$$$$|  $$$$$$ | $$| $$  \\ $$\n"
                +     "| $$\\  $$ | $$_____/ \\____  $$| $$| $$  | $$\n"
                +     "| $$ \\  $$|  $$$$$$$ /$$$$$$$/| $$| $$  | $$\n"
                +     "|__/  \\__/ \\_______/|_______/ |__/|__/  |__/ ";

        System.out.println("Hello from\n" + logo);
        System.out.println(DIVIDER);
        String s1 = String.format("%50s","What's your name?");
        System.out.println(s1);
    }
}
