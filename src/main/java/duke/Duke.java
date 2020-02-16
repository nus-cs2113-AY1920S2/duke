package duke;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    public static final String FORMAT_LINE = "------------------------------------";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String INVALID_COMMAND = "\uD83D\uDE22 OOPS!!! This is an invalid command, please type a valid command";
    public static final String GREETING = String.format("%s\n%s", "Hello, I'm duke.Duke!", "What can I do for you?");
    public static final String TODO_DESCRIPTION = "todo <Task Name>";
    public static final String DEADLINE_DESCRIPTION = "deadline <Task Name> /by <Deadline>";
    public static final String INVALID_DONE = "# of the task is invalid\n";
    public static final String DONE_DESCRIPTION = "done <Task #>";
    public static final String CORRECT_FORMAT = "The correct format should be:\n";
    public static final String INVALID_DESCRIPTION = "The description of the task is invalid\n";
    public static final String EVENT_DESCRIPTION = "event <Task Name> /at <Timeslot>";

    private static boolean hasChanges = false;

    private TaskManager manager = new TaskManager();

    public static void printFormat(String str) {
        System.out.println(FORMAT_LINE);
        System.out.println(str);
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void exitDuke() {
        if(hasChanges) {
            try {
                manager.writeToFile("data/duke.txt");
            } catch (IOException e) {
                printFormat("\uD83D\uDE22 OOPS!!! Something went wrong when writing to file!");
            }
        }
        printFormat(GOODBYE);
    }

    public void runDuke() {
        Scanner in = new Scanner(System.in);
        String command;

        try {
            manager.loadFile("data/duke.txt");
        } catch (FileNotFoundException e) {
            printFormat("\uD83D\uDE22 OOPS!!! File not found!");
        }
        while(in.hasNextLine()) {
            command = in.nextLine();
            String[] commands = command.split(" ", 2);

            if (commands[0].equalsIgnoreCase("bye")) {
                return;
            } else if (commands[0].equalsIgnoreCase("list")) {
                manager.listTask();
            } else if (commands[0].equalsIgnoreCase("done")) {
                try {
                    manager.markTask(Integer.parseInt(commands[1]));
                    hasChanges = true;
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    printFormat(INVALID_DONE + CORRECT_FORMAT + DONE_DESCRIPTION);
                }
            } else if (commands[0].equalsIgnoreCase("todo")) {
                try {
                    manager.addTodo(commands[1]);
                    hasChanges = true;
                } catch (IndexOutOfBoundsException e) {
                    printFormat(INVALID_DESCRIPTION + CORRECT_FORMAT + TODO_DESCRIPTION);
                }
            } else if (commands[0].equalsIgnoreCase("deadline")) {
                try {
                    manager.addDeadline(commands[1]);
                    hasChanges = true;
                } catch (IndexOutOfBoundsException e) {
                    printFormat(INVALID_DESCRIPTION + CORRECT_FORMAT + DEADLINE_DESCRIPTION);
                }
            } else if (commands[0].equalsIgnoreCase("event")) {
                try {
                    manager.addEvent(commands[1]);
                    hasChanges = true;
                } catch (IndexOutOfBoundsException e) {
                    printFormat(INVALID_DESCRIPTION + CORRECT_FORMAT + EVENT_DESCRIPTION);
                }
            } else if (commands[0].equalsIgnoreCase("delete")) {
                try {
                    manager.delete(Integer.parseInt(commands[1]));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    printFormat(INVALID_DONE + CORRECT_FORMAT + DONE_DESCRIPTION.replace("done", "delete"));
                }
            } else {
                printFormat(INVALID_COMMAND);
            }
        }
    }

    public void greet() {
        printFormat(GREETING);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo);
        Duke duke = new Duke();
        duke.greet();
        duke.runDuke();
        duke.exitDuke();
    }
}
