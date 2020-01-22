import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KJ\n" + "How can I help you today?");
        String cmd;
        boolean isAsking = true;
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;
        do {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isAsking = false;
            } else if (cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int x = 0; x < count; x++) {
                    System.out.println((x+1) + ".[" + list[x].getStatus() + "] " + list[x].getDescription());
                }
            } else if (cmd.contains("done")) {
                String[] phrases = cmd.split(" ");
                int index = Integer.parseInt(phrases[1]);
                list[index-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + list[index-1].getStatus() + "] " + list[index-1].getDescription());
            } else {
                list[count] = new Task(cmd);
                count += 1;
                System.out.println("added: " + cmd);
            }
        } while (isAsking);
    }
}
