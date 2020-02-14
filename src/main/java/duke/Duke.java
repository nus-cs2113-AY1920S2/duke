import duke.*;
import duke.command.*;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;

import java.util.ArrayList;
import java.util.Scanner;

// unicode-character reference list: \u2717 = ✗, \u2713 ✓

public class Duke {
    public static void main(String[] args) throws DukeException {
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
                try {
                    list.add(new Todo(command));
                    list.get(list.size()-1).printMessage(list.size());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("deadline\\s.*")){ //Level 4: Deadlines
                try {
                    list.add(new Deadline(command));
                    list.get(list.size()-1).printMessage(list.size());
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("event\\s.*")) { //Level 4: Events
                try {
                    list.add(new Event(command));
                    list.get(list.size()-1).printMessage(list.size());
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("bye")) { //Terminate
                processByeCommand();
                return;
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________");
            }
        }
    }

    private static void processDoneCommand(ArrayList<Task> list, String command) {
        int listIndex = Integer.parseInt(command.replaceAll("[^\\d]", ""));
        list.get(listIndex - 1).filteredTask = list.get(listIndex - 1).filteredTask.replace("✗", "✓");
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

