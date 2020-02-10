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
            /* character-unicode list
            \u2717 ✗
            \u2713 ✓
            */

            switch (command) {
            case "list":
                System.out.println("    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i+1) + "." + list.get(i));
                }
                System.out.print("    ____________________________________________________________\n");
                break;
            case "bye":
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                return;
            default:
                if (command.matches("done\\s\\d+")) {
                    int listIndex = Integer.parseInt(command.replaceAll("[^\\d]",""));
                    list.set(listIndex-1,list.get(listIndex-1).replace("✗","✓"));
                    System.out.println("    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done:\n       " + list.get(listIndex - 1)
                            + "\n    ____________________________________________________________");

                } else {

                System.out.println("    ____________________________________________________________\n     added: " +
                        command + "\n    ____________________________________________________________");
                list.add("[✗] " + command);
                }
                break;
            }
        }


    }
}

