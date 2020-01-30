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
                    System.out.println((x+1) + "." + instructions[x]);
                }
            } else if (command.contains("done")) {
                String[] phrases = command.split(" ");
                int index = Integer.parseInt(phrases[1]);
                instructions[index-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + instructions[index-1].getStatus() + "] " + instructions[index-1].getDescription());
            } else {
                String[] phrases = command.split(" ");
                switch (phrases[0]) {
                case "deadline":
                    int index = command.indexOf("/by");
                    String description = command.substring(9, index-1);
                    String by = command.substring(index+4);
                    instructions[count] = new Deadline(description,by);
                    count += 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + instructions[count-1]);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    break;
                case "event":
                    index = command.indexOf("/at");
                    description = command.substring(6, index-1);
                    String duration = command.substring(index+4);
                    instructions[count] = new Event(description,duration);
                    count += 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + instructions[count-1]);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    break;
                case "todo":
                    description = command.substring(5);
                    instructions[count] = new Todo(description);
                    count += 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + instructions[count-1]);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    break;
                }
            }
        } while (shouldContinue);
    }
}
