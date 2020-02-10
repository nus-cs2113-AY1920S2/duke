import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        while (!command.equals("bye")) {
            System.out.println("    ____________________________________________________________\n     " +
                    command + "\n    ____________________________________________________________\n");
            command = scanner.next();
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}

