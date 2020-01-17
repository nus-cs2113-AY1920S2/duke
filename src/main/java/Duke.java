import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        String input;
        Scanner reader = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        welcome(name);
        printLine();

        do {
            input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(input);
            }
            printLine();
        } while (!input.equals("bye"));
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void welcome(String name) {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }
}
