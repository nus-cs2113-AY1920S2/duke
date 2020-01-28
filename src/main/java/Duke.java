import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String command;
        String[] tasks = new String[100];
        int taskNumber = 0;
        boolean[] isDone = new boolean[100];

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        loop: do {
            Scanner input = new Scanner(System.in);
            command = input.next();

            switch(command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break loop;
            case "list":
                for (int i = 0; i < taskNumber; ++i) {
                    String doneStatus;
                    if (isDone[i]) {
                        doneStatus = "[Done] ";
                    } else {
                        doneStatus = "[Not Done] ";
                    }
                    System.out.println(i+1 + "." + doneStatus + tasks[i]);
                }
                break;
            case "done":
                command = input.next();
                int i = Integer.parseInt(command);
                isDone[i-1] = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[Done] " + tasks[i-1]);
                break;
            default:
                command = command + input.nextLine();
                System.out.println("added: " + command);
                tasks[taskNumber] = command;
                ++taskNumber;
                break;
            }
        } while (command != "bye");
    }
}
