import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String MISSING_ITEM = "Please enter the task description to be added!";
    public static final String MISSING_TIME_PERIOD = "Please enter a time period for the task!";
    public static final String ITEM_OUT_OF_RANGE = "Item requested is out of range! Try another item.";
    public static final String MISSING_ITEM_NUMBER = "Enter an item number to be marked done!";
    public static final String INVALID_COMMANDS = "No such commands! Please enter a valid command!";

    public static void main(String[] args) {
        // Create Scanner object
        Scanner myScanner = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();
        printWelcomeMessage();
        String userInput;

        do {
            System.out.print("USER:");
            userInput = myScanner.nextLine();
            printNewLine();

            String[] commands = userInput.trim().split(" ");

            switch (commands[0]) {
            case "bye":
                break;
            case "list":
                printListOfTasks(myList);
                break;
            case "done":
                try {
                    markTaskAsDone(myList, commands);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ITEM_OUT_OF_RANGE);
                } catch (NumberFormatException e) {
                    System.out.println(MISSING_ITEM_NUMBER);
                }
                break;
            case "todo":
                try {
                    addNewToDo(myList, commands);
                } catch (DukeException e) {
                    System.out.println(MISSING_ITEM);
                }
                break;
            case "deadline":
                try {
                    addNewDeadline(myList, commands);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(MISSING_TIME_PERIOD);
                } catch (DukeException e) {
                    System.out.println(MISSING_ITEM);
                }
                break;
            case "event":
                try {
                    addNewEvent(myList, commands);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(MISSING_TIME_PERIOD);
                } catch (DukeException e) {
                    System.out.println(MISSING_ITEM);
                }
                break;
            default:
                try {
                    throw new DukeException(INVALID_COMMANDS);
                } catch (DukeException e) {
                    System.out.println(INVALID_COMMANDS);
                }
            }
            printNewLine();
        } while (!userInput.equals("bye"));

        cleanUpAndExit(myScanner);
    }

    public static void addNewDeadline(ArrayList<Task> myList, String[] commands)
            throws DukeException, IndexOutOfBoundsException {
        if (isInvalidLength(commands)) {
            throw new DukeException();
        } else {
            // parsing userInput, remove first word "deadline"
            String[] deadlineDetails = removeFirstWord(commands).split("/");
            myList.add(new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim()));
            printSuccessfulAddMessage(myList.get(Task.getNumberOfTasksInList() - 1)
                    .getDescriptionInListFormat());
        }
    }

    public static void addNewToDo(ArrayList<Task> myList, String[] commands)
            throws DukeException {
        if (isInvalidLength(commands)) {
            throw new DukeException();
        } else {
            String parsedUserInput = removeFirstWord(commands);
            myList.add(new Todo(parsedUserInput.trim()));
            // gets last newly added Task
            printSuccessfulAddMessage(myList.get(Task.getNumberOfTasksInList() - 1)
                    .getDescriptionInListFormat());
        }
    }

    public static void addNewEvent(ArrayList<Task> myList, String[] commands)
            throws IndexOutOfBoundsException, DukeException {
        if (isInvalidLength(commands)) {
            throw new DukeException();
        } else {
            // parsing userInput, remove first word "event"
            String[] eventDetails = removeFirstWord(commands).split("/");

            // testing IndexOutOfBoundsException
            myList.add(new Events(eventDetails[0].trim(), eventDetails[1].trim()));
            printSuccessfulAddMessage(myList.get(Task.getNumberOfTasksInList() - 1)
                    .getDescriptionInListFormat());

        }
    }

    public static void markTaskAsDone(ArrayList<Task> myList, String[] commands)
            throws IndexOutOfBoundsException, NumberFormatException {
        // testing NumberFormatException
        int itemIndexRequested = Integer.parseInt(commands[1]) - 1;

        // testing IndexOutOfBoundsException
        myList.get(itemIndexRequested).markAsDone();
        System.out.println(myList.get(itemIndexRequested)
                .getDoneResponseMessage(itemIndexRequested + 1));
    }

    public static boolean isInvalidLength(String[] commands) {
        return commands.length < 2;
    }

    public static void printListOfTasks(ArrayList<Task> myList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1,
                    myList.get(i).getDescriptionInListFormat()));
        }
    }

    public static void printSuccessfulAddMessage(String taskDetails) {
        System.out.println(String.format("Added the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    /**
     * Returns the user input in a String format without the first command
     * @param command the Array of split commands entered
     * @return description of Task as a String
     */
    private static String removeFirstWord(String[] command) {
        // parsing userInput, remove first word
        String[] detailsOfTask = Arrays.copyOfRange(command, 1, command.length);
        StringBuilder sb = new StringBuilder();
        for (String word : detailsOfTask) {
            sb.append(word).append(" ");
        }
        return (sb.toString());
    }

    /**
     * Prints the partition between each user response interaction
     */
    public static void printNewLine() {
        String NEW_LINE = "____________________________________________________________";
        System.out.println(NEW_LINE);
    }

    public static void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO + "What can I do for you?\n");
    }

    public static void cleanUpAndExit(Scanner myScanner) {
        myScanner.close();
        System.out.println("Exiting DUKE\n" + LOGO);
    }
}
