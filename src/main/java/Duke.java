import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        do {
            Scanner input = new Scanner(System.in);
            command = input.next();
            boolean isBye = command.equals("bye");
            if (isBye) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(command);
            }
        } while (command != "bye");
    }
}
