import java.util.Scanner;

public class Duke {

    public static void printIndentation() {
        System.out.print("    ");
    }

    public static void greets() {
        printIndentation();
        System.out.println("Hello! I'm Duke");
        printIndentation();
        System.out.println("What can I do for you?");
    }

    public static void printLines() {
        printIndentation();
        System.out.println("---------------------------------------");
    }

    public static void exits() {
        printIndentation();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String readFromUser() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        return userInput;
    }

    public static void echoUntilBye() {
        while (true) {
            String text = readFromUser();
            if (text.equals("bye")) {
                break;
            }
            printLines();
            printIndentation();
            System.out.println(text);
            printLines();
        }
        exits();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLines();
        greets();
        printLines();
        echoUntilBye();
        printLines();
    }
}
