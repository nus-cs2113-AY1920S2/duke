import duke.command.*;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        loadListDataFromDisk(list);

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) { //Terminate
                processByeCommand();
                return;
            } else if (command.matches("done\\s+\\d+(\\s+)?")) { //Level 3: Mark as Done
                processDoneCommand(list, command);
            } else if (command.matches("delete\\s+\\d+(\\s+)?")) { //Level 6: Delete
                processDeleteCommand(list, command);
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
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________");
            }
            updateListDataOnDisk(list);
        }
    }

    private static void loadListDataFromDisk(ArrayList<Task> list) {
        try {
            File f = new File("data/duke_list.txt");
            Scanner reader = new Scanner(f);
            while (reader.hasNext()) {
                list.add(new Task(reader.nextLine()));
            }
        } catch (FileNotFoundException ignored) {}
    }

    private static void updateListDataOnDisk(ArrayList<Task> list) {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) {}
        try {
            Files.createFile(Paths.get("data/duke_list.txt"));
        } catch (IOException ignored) {}
        try {
            FileWriter fw = new FileWriter("data/duke_list.txt");
            list.forEach((n) -> {
                try {
                    fw.write(n.filteredTask + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDeleteCommand(ArrayList<Task> list, String command) {
        try {
            System.out.println("    ____________________________________________________________\n"
                    + "     Noted. I've removed this task: \n" + "       "
                    + list.get(Integer.parseInt(command.replaceAll("[^\\d]",""))-1).filteredTask
                    + "\n     Now you have " + (list.size()-1) + (list.size()>1?" tasks":" task") + " in the list.\n"
                    + "    ____________________________________________________________");
            list.remove(Integer.parseInt(command.replaceAll("[^\\d]",""))-1);
        } catch (NumberFormatException e){
            System.out.println("    ____________________________________________________________\n"
                    + "     Task does not exist!\n"
                    + "    ____________________________________________________________");
        }
    }

    private static void processDoneCommand(ArrayList<Task> list, String command) {
        try {
            int listIndex = Integer.parseInt(command.replaceAll("[^\\d]", ""));
            list.get(listIndex - 1).filteredTask = list.get(listIndex - 1).filteredTask.replace("✗", "✓");
            System.out.println("    ____________________________________________________________\n"
                    + "     Nice! I've marked this task as done:\n       " + list.get(listIndex - 1).filteredTask
                    + "\n    ____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    ____________________________________________________________\n"
                    + "     Task does not exist!\n"
                    + "    ____________________________________________________________");
        }
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

