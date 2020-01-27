import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        String[] tasks = new String[100];
        int taskNumber = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        do {
            Scanner input = new Scanner(System.in);
            command = input.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < taskNumber; ++i) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            } else {
                System.out.println("added: " + command);
                tasks[taskNumber] = command;
                ++taskNumber;
            }
        } while (command != "bye");
    }
}
