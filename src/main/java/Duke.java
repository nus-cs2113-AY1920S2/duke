import java.util.Scanner;

public class Duke {
    private static final String DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static ToDoList tasks = new ToDoList();

    public static void main(String[] args) {
        printWelcomeBanner();

        Scanner scanner = new Scanner(System.in);

        // Loop terminates on receiving a "bye" command, which calls System.exit(0)
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print("> ");
            String fullCommand = scanner.nextLine();

            try {
                parseCommand(fullCommand);
            } catch (DukeException e) {
                System.err.println(e.toString());
            } finally {
                printDividerLine();
            }

            /*
            if (inputLC.equals("bye")) {
                // end duke program
                printByeMessage();
                break;
            } else if (inputLC.equals("list")) {
                // list all existing tasks and task statuses
                list.printList();
            } else if (inputLC.startsWith("done ")){
                // mark a task as done
                int taskIndex = Character.getNumericValue(input.charAt(5)) - 1;
                list.markAsDone(taskIndex);
            } else {
                // add user input to list
                list.addToList(input);
            }
            printDividerLine();
             */
        }
    }

    private static void parseCommand(String fullCommand) throws DukeException {
        String[] commandTokens = fullCommand.split(" ");
        String commandType = commandTokens[0].toLowerCase();

        switch(commandType) {
        case "bye":
            // exit duke
            printByeMessage();
            System.exit(0);

        case "list":
            // show all tasks in the list
            tasks.printList();
            break;

        case "done":
            // mark a task as done
            int taskIndex = Character.getNumericValue(fullCommand.charAt(5)) - 1;
            tasks.markAsDone(taskIndex);
            break;

        case "todo":
            // add todo to tasks
            // input follows format <taskType> <taskName>
            String description = null;
            try {
                description = fullCommand.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty");
            }

            tasks.addToList(new ToDos(description));
            break;

        case "deadline":
            // add deadline to tasks
            // input follows format <taskType> <taskName> /<date>
            int byIndex = fullCommand.indexOf(" /by ");
            if (byIndex < 0) {
                throw new DukeException("The description and date of a deadline cannot be empty");
            }
            String deadlineName = fullCommand.substring(9, byIndex).trim();
            String deadlineDate = fullCommand.substring(byIndex + 5).trim();
            tasks.addToList(new Deadlines(deadlineName, deadlineDate));
            break;

        case "event":
            // add event to tasks
            // input follows format <taskType> <taskName> /<date>
            int atIndex = fullCommand.indexOf(" /at ");
            if (atIndex < 0) {
                throw new DukeException("The description and date of a deadline cannot be empty");
            }
            String eventName = fullCommand.substring(6, atIndex).trim();
            String eventDate = fullCommand.substring(atIndex + 5).trim();
            tasks.addToList(new Events(eventName, eventDate));
            break;

        default:
            throw new DukeException("I'm not very sure what that means...");
        }
    }

    private static void printDividerLine() {
        System.out.println("_________________________________________________");
    }

    private static void printWelcomeBanner() {
        printDividerLine();
        System.out.println("This is\n" + DUKE_LOGO);
        System.out.println("How can I help you today?");
        printDividerLine();
    }

    private static void printByeMessage() {
        System.out.println("Goodbye");
        printDividerLine();
    }
}
