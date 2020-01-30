import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        boolean isExit = false;
        Task[] list = new Task[MAX_TASK];
        int itemCount = 0;
        do {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine(); // Get string input
            if(input.equals("bye")) { // Exit
                printExitMessage();
                isExit = true;
            } else if(input.equals("list")) { // List tasks
                printBorder();
                System.out.println("    Here are the tasks in your list:");
                for(int i = 0; i < itemCount; ++i) { // Print list of tasks
                    System.out.println("    " + (i + 1) + ". [" + list[i].getStatusIcon() + "] " + list[i].getTask() );
                }
                printBorder();
            } else if(input.startsWith("done") ) { // Mark task as done
                printBorder();
                System.out.println("    Nice! I've marked this task as done: ");
                String index = input.substring(5, input.length() );
                int taskIndex = Integer.parseInt(index) - 1;
                list[taskIndex].setDone(true);
                System.out.println("    " + " [" + list[taskIndex].getStatusIcon() + "] " + list[taskIndex].getTask() ); // Print task marked as done
                printBorder();
            }
            else { // Add task
                printBorder();
                list[itemCount] = new Task(input);
                itemCount++;
                System.out.println("    added: " + input); // Print task added
                printBorder();
            }
        } while (isExit == false);
    }

    private static void printBorder() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" _                \n"
                + "| |               \n"
                + "| |__  _   _  ___ \n"
                + "| '_ \\| | | |/ _ \\\n"
                + "| |_) | |_| |  __/\n"
                + "|_.__/ \\__, |\\___|\n"
                + "        __/ |     \n"
                + "       |___/      \n");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}