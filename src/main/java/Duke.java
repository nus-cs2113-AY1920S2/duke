import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printGreeting();
        echoCommands();
        printExitMessage();
    }

    private static void echoCommands() {
        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println("    " + line);
        }
    }

    private static void printExitMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    private static void printGreeting() {
        System.out.println("    Hello I'm Yapyap.");
        System.out.println("    What can I do for you?");
    }
}
