import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KJ\n" + "How can I help you today?");
        String cmd;
        boolean isAsking = true;
        Scanner input = new Scanner(System.in);
        do {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isAsking = false;
            } else {
                System.out.println(cmd);
            }
        } while (isAsking);
    }
}
