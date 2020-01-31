import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] lines = new String[100];
        int count = 0;
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        while (!string.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            if (!string.equals("list")) {
                lines[count] = string;
                count++;
                System.out.println("     added: " + string);
            } else {
                for (int i = 0; i < count; i++) {
                    System.out.println("     " + (i + 1) + ". " + lines[i]);
                }
            }
            System.out.println("    ____________________________________________________________");
            string = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
