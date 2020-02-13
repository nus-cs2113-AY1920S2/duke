import java.util.ArrayList;
import java.util.Scanner;

// unicode-character reference list: \u2717 = ✗, \u2713 ✓

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        while (true) {
            String command = scanner.nextLine();

            if (command.matches("done\\s\\d+")) { //Level 3: Mark as Done
                processDoneCommand(list, command);
            } else if (command.equals("list")) { //Level 2: List (Add-function is included in level 4)
                processListCommand(list);
            } else if (command.matches("todo\\s.*")){ //Level 4: ToDos
                list.add(new Todo(command));
                list.get(list.size()-1).printMessage(list.size());
            } else if (command.matches("deadline\\s.*")){ //Level 4: Deadlines
                list.add(new Deadline(command));
                list.get(list.size()-1).printMessage(list.size());
            } else if (command.matches("event\\s.*")) { //Level 4: Events
                list.add(new Event(command));
                list.get(list.size()-1).printMessage(list.size());
            } else if (command.equals("bye")) { //Terminate
                processByeCommand();
                return;
            }
        }
    }

    private static void processDoneCommand(ArrayList<Task> list, String command) {
        int listIndex = Integer.parseInt(command.replaceAll("[^\\d]",""));
        list.get(listIndex-1).filteredTask = list.get(listIndex-1).filteredTask.replace("✗","✓");
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n       " + list.get(listIndex - 1).filteredTask
                + "\n    ____________________________________________________________");
    }

    private static void processByeCommand() {
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

    private static void processListCommand(ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________\n"
                + "     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "." + list.get(i).filteredTask);
        }
        System.out.print("    ____________________________________________________________\n");
    }

}

