import java.util.List;

public class Printer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printIndentation() {
        System.out.print("    ");
    }

    public static void printStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Printer.printLines();
        Printer.printGreetings();
        Printer.printLines();
    }

    public static void printGreetings() {
        printIndentation();
        System.out.println("Hello! I'm Duke (￣▽￣*)ゞ");
        printIndentation();
        System.out.println("What can I do for you?");
    }

    public static void printLines() {
        printIndentation();
        System.out.print("---------------------------------------");
        System.out.println("---------------------------------------");
    }

    public static void printBye() {
        System.out.println("Bye ~ Hope to see you again soon! o(〒﹏〒)o");
    }

    public static void printTasks(List<Task> myList) {
        printLines();
        printIndentation();
        if (myList.isEmpty()) {
            System.out.println("List is empty (°ロ°) !");
            printLines();
            return;
        }
        System.out.println("Here's your list (◕‿◕)♡ ~ ");
        printList(myList);
        printLines();
    }

    public static void printTasks(String command, List<Task> myList) {
        printLines();
        printIndentation();
        if (myList.isEmpty()) {
            System.out.println("There are no matching tasks in your list (°ロ°) !");
            printLines();
            return;
        }
        System.out.println("Here's what I found (◕‿◕)♡ ~ ");
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
            System.out.printf("Nice! I've marked this task as done ヽ(・∀・)ﾉ :\n");
            printIndentation();
            System.out.printf("  %s\n", task);
            break;

        case "delete":
            System.out.printf("Ok! I have deleted this task ヽ(・∀・)ﾉ :\n");
            printIndentation();
            System.out.printf("  %s\n", task);
            printIndentation();
            System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
            break;

        case "todo":
            System.out.println("Got it! I've added this task ＠＾◡＾) :");
            printIndentation();
            System.out.println("  " + task);
            printIndentation();
            System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
            break;

        case "deadline":
            System.out.println("Oh a deadline huh! Don't worry, I have added this task <(￣︶￣)> :");
            printIndentation();
            System.out.println("  " + task);
            printIndentation();
            System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
            break;

        case "event":
            System.out.println("Huuu what a busy guy! I have marked this in your list~ <(￣︶￣)> :");
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
        System.out.println("Error.. ٩(× ×)۶");
        printLines();
    }

    public static void printEmptyDescriptionError(String command) {
        printLines();
        printIndentation();
        System.out.println("Σ(°ロ°) The description of " + command + " cannot be empty!~ ٩(× ×)۶ ");
    }

    public static void printFormatError(String command) {
        printLines();
        printIndentation();
        System.out.println("Σ(°ロ°) There seems to be some problem with the format of " + command + " !~ ٩(× ×)۶ ");
    }

    public static void printHint(String command) {
        printIndentation();
        System.out.printf("Here's a hint on how %s%s%s works:\n\n", ANSI_BLUE, command, ANSI_RESET);

        printIndentation();
        printIndentation();

        switch (command) {
        case "event":
            System.out.println(ANSI_BLUE + command + ANSI_RESET + "(space)<task>(space)/at(space)<at>");
            printIndentation();
            printIndentation();
            System.out.println("Example: " + ANSI_BLUE + "event" + ANSI_RESET + " team meeting /at 2 August 2-4pm");
            break;

        case "find":
        case "todo":
            System.out.println(ANSI_BLUE + command + ANSI_RESET + "(space)<task>");
            printIndentation();
            printIndentation();
            System.out.println("Example: " + ANSI_BLUE + command + ANSI_RESET + " read book");
            break;

        case "deadline":
            System.out.println(ANSI_BLUE + command + ANSI_RESET + "(space)<task>(space)/by(space)<date>");
            printIndentation();
            printIndentation();
            System.out.println("Example: " + ANSI_BLUE + "deadline" + ANSI_RESET + " read book /by Sunday");
            break;

        }

        printLines();
    }

    public static void printUnknownCommandError(String command) {
        printLines();
        printIndentation();
        System.out.printf("I'm sorry I don't understand this command: %s%s%s *(>д<)*\n", ANSI_RED, command, ANSI_RESET);
        printLines();
    }
}
