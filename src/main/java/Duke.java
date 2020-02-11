import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>(100);

        while (true) {
            String command = scanner.nextLine();

            // unicode-character reference list: \u2717 = ✗, \u2713 ✓
            if (command.matches("done\\s\\d+")) { //Level 3: Mark as Done
                processDoneCommand(list, command);
            } else if (command.equals("list")) { //Level 2: List (Add-function is included in level 4)
                processListCommand(list);
            } else if (command.matches("todo\\s.*")){ //Level 4: ToDos
                processToDoCommand(list, command);
            } else if (command.matches("deadline\\s.*")){ //Level 4: Deadlines
                processDeadLineCommand(list, command);
            } else if (command.matches("event\\s.*")) { //Level 4: Events
                processEventCommand(list, command);
            } else if (command.equals("bye")) { //Terminate
                processByeCommand();
                return;
            }
        }
    }

    private static void processByeCommand() {
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

    private static void processDoneCommand(ArrayList<String> list, String command) {
        int listIndex = Integer.parseInt(command.replaceAll("[^\\d]",""));
        list.set(listIndex-1,list.get(listIndex-1).replace("✗","✓"));
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n       " + list.get(listIndex - 1)
                + "\n    ____________________________________________________________");
    }

    private static void processListCommand(ArrayList<String> list) {
        System.out.println("    ____________________________________________________________\n"
                + "     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "." + list.get(i));
        }
        System.out.print("    ____________________________________________________________\n");
    }

    private static void processToDoCommand(ArrayList<String> list, String command) {
        String filteredCommand = "[T][✗] " + command.replaceFirst("todo\\s","");
        list.add(filteredCommand);
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n       "
                + filteredCommand
                + "\n     Now you have " + list.size() + " tasks in the list."
                + "\n    ____________________________________________________________");
    }

    private static void processDeadLineCommand(ArrayList<String> list, String command) {
        String filteredCommand = "[D][✗] "
                + command.replaceFirst("deadline\\s","").replaceFirst("/by","(by:")
                + ")";
        list.add(filteredCommand);
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n       "
                + filteredCommand
                + "\n     Now you have " + list.size() + " tasks in the list."
                + "\n    ____________________________________________________________");
    }

    private static void processEventCommand(ArrayList<String> list, String command) {
        String filteredCommand = "[E][✗] "
                + command.replaceFirst("event\\s","").replaceFirst("/at","(at:")
                + ")";
        list.add(filteredCommand);
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n       "
                + filteredCommand
                + "\n     Now you have " + list.size() + " tasks in the list."
                + "\n    ____________________________________________________________");
    }
}

