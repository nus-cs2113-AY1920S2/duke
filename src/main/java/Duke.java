import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeBanner();

        Scanner scanner = new Scanner(System.in);
        ToDoList list = new ToDoList();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                printByeMessage();
                break;
            } else if (input.toLowerCase().equals("list")) {
                list.viewList();
                printDividerLine();
            } else {
                // add user input to list
                list.addToList(input);
                printDividerLine();
            }
        }
    }

    private static void printDividerLine() {
        System.out.println("_________________________________________________");
    }

    // am I supposed to declare helper functions this way?
    // seems abit dodgy
    private static void printWelcomeBanner() {
        printDividerLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("This is\n" + logo);
        System.out.println("How can I help you today?");
        printDividerLine();
    }

    private static void printByeMessage() {
        System.out.println("Goodbye");
        printDividerLine();
    }
}
