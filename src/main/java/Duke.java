import java.util.Scanner;

public class Duke {
    private static final String DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        printWelcomeBanner();

        Scanner scanner = new Scanner(System.in);
        ToDoList list = new ToDoList();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String inputLC = input.toLowerCase();
            if (inputLC.equals("bye")) {
                // end duke program
                printByeMessage();
                break;
            } else if (inputLC.equals("list")) {
                // list all existing tasks and task statuses
                list.printList();
            } else if (inputLC.startsWith("done ")){
                // mark a task as done
                int taskIndex = Character.getNumericValue(input.charAt(5)) - 1;
                list.markAsDone(taskIndex);
            } else {
                // add user input to list
                list.addToList(input);
            }
            printDividerLine();
        }
    }

    private static void printDividerLine() {
        System.out.println("_________________________________________________");
    }

    // am I supposed to declare helper functions this way?
    // seems abit dodgy
    private static void printWelcomeBanner() {
        printDividerLine();
        System.out.println("This is\n" + DUKE_LOGO);
        System.out.println("How can I help you today?");
        printDividerLine();
    }

    private static void printByeMessage() {
        System.out.println("Goodbye");
        printDividerLine();
    }
}
