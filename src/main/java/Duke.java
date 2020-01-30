import java.util.Scanner;

public class Duke {

    private static final int MAXIMUM_TASKS = 100;

    private static boolean shouldContinue;

    private static String command;

    private static String action;

    private static Scanner input;

    private static Task[] instructions;

    private static int count;

    public static void main(String[] args) {
        initDuke();
        displayWelcome();
        while (shouldContinue) {
            readInput();
            setAction();
            processCommand();
        }
    }

    private static void processCommand() {
        switch(action) {
        case "bye":
            sayBye();
            break;
        case "list":
            listTasks();
            break;
        case "done":
            completeTask();
            break;
        case "deadline":
            setDeadline();
            confirmTask();
            break;
        case "event":
            createEvent();
            confirmTask();
            break;
        case "todo":
            addTodo();
            confirmTask();
            break;
        }
    }

    private static void addTodo() {
        String description = command.substring(5);
        instructions[count] = new Todo(description);
        count += 1;
    }

    private static void createEvent() {
        int index = command.indexOf("/at");
        String description = command.substring(6, index - 1);
        String duration = command.substring(index + 4);
        instructions[count] = new Event(description, duration);
        count += 1;
    }

    private static void setDeadline() {
        int index = command.indexOf("/by");
        String description = command.substring(9, index - 1);
        String by = command.substring(index + 4);
        instructions[count] = new Deadline(description, by);
        count += 1;
    }
    private static void completeTask() {
        String[] phrases = command.split(" ");
        int index = Integer.parseInt(phrases[1]);
        instructions[index-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + instructions[index-1]);
    }

    private static void setAction() {
        String[] phrases = command.split(" ");
        action = phrases[0];
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i+1) + "." + instructions[i]);
        }
    }

    private static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        shouldContinue = false;
    }

    private static void readInput() {
        command = input.nextLine();
    }

    private static void initDuke() {
        shouldContinue = true;
        input = new Scanner(System.in);
        instructions = new Task[MAXIMUM_TASKS];
        count = 0;
    }

    private static void confirmTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + instructions[count-1]);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    private static void displayWelcome() {
        System.out.println("Hello! I'm KJ\n" + "How can I help you today?");
    }
}
