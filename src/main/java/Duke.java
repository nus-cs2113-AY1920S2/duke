import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KJ\n" + "How can I help you today?");
        String cmd;
        boolean isAsking = true;
        Scanner input = new Scanner(System.in);
        String[] tasks = new String[100];
        int count = 0;
        do {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isAsking = false;
            } else if (cmd.equals("list")) {
                for (int x = 0; x < count; x++) {
                    System.out.println((x+1) + ". " + tasks[x]);
                }
            } else {
                tasks[count] = cmd;
                count += 1;
                System.out.println("added: " + cmd);
            }
        } while (isAsking);
    }
}
