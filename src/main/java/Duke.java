import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KJ\n" + "How can I help you today?");
        String command;
        boolean shouldContinue = true;
        Scanner input = new Scanner(System.in);
        Task[] instructions = new Task[100];
        int count = 0;
        do {
            command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                shouldContinue = false;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int x = 0; x < count; x++) {
                    System.out.println((x+1) + ".[" + instructions[x].getStatus() + "] " + instructions[x].getDescription());
                }
            } else if (command.contains("done")) {
                String[] phrases = command.split(" ");
                int index = Integer.parseInt(phrases[1]);
                instructions[index-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + instructions[index-1].getStatus() + "] " + instructions[index-1].getDescription());
            } else {
                instructions[count] = new Task(command);
                count += 1;
                System.out.println("added: " + command);
            }
        } while (shouldContinue);
    }
}
