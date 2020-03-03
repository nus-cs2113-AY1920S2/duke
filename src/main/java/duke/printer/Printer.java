package duke.printer;

import duke.commands.Command;
import duke.storage.Storage;
import duke.tasks.Task;

import java.util.List;

public class Printer {

    public static void printIndentation() {
        System.out.print("    ");
    }

    public static void printStart(boolean isReturnUser) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Printer.printLines();
        Printer.printGreetings(isReturnUser);
        Printer.printLines();
    }

    public static void printGreetings(boolean isReturnUser) {
        printIndentation();

        if (isReturnUser) {
            System.out.println("Hello! I'm Duke :>");
            printIndentation();
            System.out.println("What can I do for you?");
            printIndentation();
            System.out.println("Type help to see what I can do! :>");

        } else {
            System.out.println("Welcome back ! ~ I missed you ;_;");
        }
    }

    public static void printLines() {
        printIndentation();
        System.out.print("---------------------------------------");
        System.out.println("---------------------------------------");
    }

    public static void printBye() {
        System.out.println("Bye ~ Hope to see you again soon! T_T");
    }

    public static void printTasks(List<Task> myList) {
        printLines();
        printIndentation();
        if (myList.isEmpty()) {
            System.out.println("List is empty o.o !");
            printLines();
            return;
        }
        System.out.println("Here's your list ;3");
        printList(myList);
        printLines();
    }

    public static void printTasks(String command, List<Task> myList) {
        printLines();
        printIndentation();
        if (myList.isEmpty()) {
            System.out.println("There are no matching tasks in your list :o !");
            printLines();
            return;
        }
        System.out.println("Here's what I found :3 ~ ");
        printList(myList);
        printLines();
    }

    public static void printList(List<Task> myList) {
        for (int i = 0; i < myList.size(); i++) {
            printIndentation();
            Task temp = myList.get(i);
            System.out.printf("%d. %s\n", i + 1, temp);
        }
    }

    public static void printConfirmationMessage(String command, Task task) {
        printLines();
        printIndentation();

        switch (command) {
        case "done":
            System.out.printf("Nice! I've marked this task as done ^-^ :\n");
            printIndentation();
            System.out.printf("  %s\n", task);
            break;

        case "delete":
            System.out.printf("Ok! I have deleted this task ^-^ :\n");
            printIndentation();
            System.out.printf("  %s\n", task);
            printIndentation();
            System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
            break;

        case "todo":
            System.out.println("Got it! I've added this task ^-^ :");
            printIndentation();
            System.out.println("  " + task);
            printIndentation();
            System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
            break;

        case "deadline":
            System.out.println("Oh a deadline huh! Don't worry, I have added this task ~.~ :");
            printIndentation();
            System.out.println("  " + task);
            printIndentation();
            System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
            break;

        case "event":
            System.out.println("Huuu what a busy guy! I have marked this in your list~ ~.~ :");
            printIndentation();
            System.out.println("  " + task);
            printIndentation();
            System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
            break;
        }
        printLines();
    }

    public static void printError() {
        printLines();
        printIndentation();
        System.out.println("Error.. x.x");
        printLines();
    }

    public static void printEmptyDescriptionError(String command) {
        printLines();
        printIndentation();
        System.out.println("The description of " + command + " cannot be empty!~ X.X");
    }

    public static void printFormatError(String command) {
        printLines();
        printIndentation();
        System.out.println("X.X There seems to be some problem with the format of " + command + " !~ X.X ");
    }

    public static void printHint(String command) {
        printIndentation();
        System.out.printf("Here's a hint on how %s works:\n\n", command);

        printIndentation();
        printIndentation();

        switch (command) {
        case "event":
            System.out.println(command + "(space)<task>(space)/at(space)<at>");
            printIndentation();
            printIndentation();
            System.out.println("Example: event team meeting /at 2 August 2-4pm");
            break;

        case "find":
            //Fallthrough
        case "todo":
            System.out.println(command + "(space)<task>");
            printIndentation();
            printIndentation();
            System.out.println("Example: " + command + " read book");
            break;

        case "deadline":
            System.out.println(command + "(space)<task>(space)/by(space)<date>");
            printIndentation();
            printIndentation();
            System.out.println("Example: deadline read book /by Sunday");
            break;

        case "delete":
            //Fallthrough
        case "done":
            System.out.println("Please ensure that the index supplied exists in the list @_@");
            System.out.println();
            printIndentation();
            printIndentation();
            System.out.println(command + "(space)<index>");
            printIndentation();
            printIndentation();
            System.out.println("Example: " + command + " 1");
            break;

        default :
            break;
        }

        printLines();
    }

    public static void printUnknownCommandError(String command) {
        printLines();
        printIndentation();
        System.out.printf("I'm sorry I don't understand this command: %s *(>_<)*\n", command);
        printLines();
    }

    public static void printHelp() {
        printLines();
        printIndentation();
        System.out.println("Here is what I can do ^-^/");
        for (String command : Command.COMMAND_LIST) {
            printIndentation();
            printIndentation();
            System.out.println("- " + command);
        }
        printLines();
    }

    public static void printFileNotFile() {
        System.out.println("File not found");
    }

    public static void printFileCorrupted() {
        System.out.print("Save file corrupted, please check your file");
    }
}
