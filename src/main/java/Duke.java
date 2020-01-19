import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeBanner();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                printByeMessage();
                break;
            } else {
                // echo user's input
                System.out.println(input);
                System.out.println("_________________________________________________");
            }
        }
    }

    // am I supposed to declare helper functions this way?
    // seems abit dodgy
    private static void printWelcomeBanner() {
        System.out.println("_________________________________________________");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("This is\n" + logo);
        System.out.println("How can I help you today?");
        System.out.println("_________________________________________________");
    }

    private static void printByeMessage() {
        System.out.println("Goodbye");
        System.out.println("_________________________________________________");
    }
}
