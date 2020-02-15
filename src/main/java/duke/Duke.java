package duke;

import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String DIVIDER = "    ____________________________________________________________";

    public static void main(String[] args) {
        printWelcomeMessage();
        runChatBot();
    }

    private static void runChatBot() {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        taskManager(input, tasks);
    }

    private static void taskManager(Scanner input, ArrayList<Task> Tasks) {
        while (true) {
            try {
                String userInput = getInput(input.nextLine());
                String[] userCommands = userInput.split(" ", 2);

                if (userCommands[0].equalsIgnoreCase("bye")) {
                    printByeMessage();
                    break;
                } else if (userCommands[0].equalsIgnoreCase("list")) {
                    printList(Tasks);
                } else if (userCommands[0].equalsIgnoreCase("done")) {
                    printDone(userCommands, Tasks);
                } else if (userCommands[0].equalsIgnoreCase("todo")) {
                    addToDo(Tasks, userCommands);
                } else if (userCommands[0].equalsIgnoreCase("event")) {
                    addEvent(Tasks, userCommands);
                } else if (userCommands[0].equalsIgnoreCase("deadline")) {
                    addDeadline(Tasks, userCommands);
                } else if (userCommands[0].equalsIgnoreCase("remove")) {
                    printRemove(userCommands, Tasks);
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(DIVIDER);
        }
    }

    private static void printRemove(String[] input, ArrayList<Task> Tasks) {
        try {
            int num = Integer.parseInt(input[1]) - 1;
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + Tasks.get(num));
            Tasks.remove(num);
            int taskCounter = Tasks.size();
            System.out.println("    Now you have " + taskCounter + " task(s) in the list.");
        } catch (NullPointerException e) {
            System.out.println("    Error: Given Index is out of bound");
        } catch (Exception e) {
            System.out.println("    Error: Insufficient detail");
        }
    }

    private static void addEvent(ArrayList<Task> tasks, String[] userCommand) {
        try {
            if (userCommand.length <= 1) {
                throw new DukeException();
            }
            String[] buffer = userCommand[1].split("/at", 2);
            if (buffer[1].trim().isEmpty()) {
                throw new DukeException();
            }
            int taskCounter = tasks.size();
            tasks.add(new Event(buffer[0].trim(), buffer[1].trim()));
            printTask(tasks.get(taskCounter), taskCounter);
        } catch (DukeException e) {
            System.out.println("    ☹ OOPS!!! The description of a event cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    ☹ OOPS!!! The place of a event cannot be empty.");
        }
    }

    private static void addDeadline(ArrayList<Task> tasks, String[] userCommand) {
        try {
            if (userCommand.length <= 1) {
                throw new DukeException();
            }
            String[] buffer = userCommand[1].split("/by", 2);
            if (buffer[1].trim().isEmpty()) {
                throw new DukeException();
            }
            int taskCounter = tasks.size();
            tasks.add(new DeadLine(buffer[0].trim(), buffer[1].trim()));
            printTask(tasks.get(taskCounter), taskCounter);
        } catch (DukeException e) {
            System.out.println("    ☹ OOPS!!! The description of a DeadLine cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    ☹ OOPS!!! The time/date of a DeadLine cannot be empty.");
        }
    }

    private static void addToDo(ArrayList<Task> tasks, String[] userCommands) {
        try {
            int taskCounter = tasks.size();
            if (userCommands[1].trim().isEmpty()){
                throw new DukeException();
            }
            tasks.add(new ToDo(userCommands[1]));
            printTask(tasks.get(taskCounter), taskCounter);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    ☹ OOPS!!! The description of a ToDo cannot be empty.");
        } catch (DukeException e) {
            System.out.println("    ☹ OOPS!!! The description of a ToDo cannot be empty.");
        }
    }

    private static void printDone(String[] input, ArrayList<Task> Tasks) {
        try {
            int num = Integer.parseInt(input[1]) - 1;
            Tasks.get(num).taskDone();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + Tasks.get(num));
        } catch (NullPointerException e) {
            System.out.println("    Error: Given Index is out of bound");
        } catch (Exception e) {
            System.out.println("    Error: Insufficient detail");
        }
    }

    private static void printTask(Task task, int taskCounter) {
        System.out.println("    Got it! I've added this task:");
        System.out.println("       " + task);
        taskCounter++;
        System.out.println("    Now you have " + taskCounter + " task(s) in the list.");
    }

    private static void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()){
            System.out.println("    The list is empty.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            int taskCounter = tasks.size();
            for (int i = 0; i < taskCounter; i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void printByeMessage() {
        System.out.println("    Bye. Hope to see you soon!");
        System.out.println(DIVIDER);
    }

    private static String getInput(String next) {
        String input = next;
        System.out.println(DIVIDER);
        return input;
    }

    private static void printWelcomeMessage() {
        String logo = "    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                "    ░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░\n" +
                "    ░░░░░░░░▄▀░░░░░░░░░░░░▄░░░░░░░▀▄░░░░░░░\n" +
                "    ░░░░░░░░█░░▄░░░░▄░░░░░░░░░░░░░░█░░░░░░░\n" +
                "    ░░░░░░░░█░░░░░░░░░░░░▄█▄▄░░▄░░░█░▄▄▄░░░\n" +
                "    ░▄▄▄▄▄░░█░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██░░\n" +
                "    ░██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██░░\n" +
                "    ░░▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██░\n" +
                "    ░░░░▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██░\n" +
                "    ░░░░░░░▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██░\n" +
                "    ░░░░░░░▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀░░\n" +
                "    ░░░░░░█▀▀█████████▀▀▀▀████████████▀░░░░\n" +
                "    ░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀░░░░░░\n" +
                "    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n";
        System.out.println(DIVIDER);
        System.out.print(logo);
        System.out.println("    Hello Nyan Cat here!");
        System.out.println(DIVIDER);
    }
}
