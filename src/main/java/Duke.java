import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<String>(100);
        while (true) {
            String command = scanner.nextLine();

            switch (command) {
            case "list":
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i+1) + ". " + list.get(i));
                }
                System.out.print("    ____________________________________________________________\n");
                break;
            case "bye":
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                return;
            default:
                System.out.println("    ____________________________________________________________\n     added: " +
                        command + "\n    ____________________________________________________________\n");
                list.add(command);
                break;
            }
        }


    }
}

