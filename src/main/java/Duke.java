import Exceptions.EmptyListException;
import Exceptions.DukeException;
import Exceptions.MissingItemIndexException;
import Exceptions.MissingDescriptionException;
import Exceptions.UnknownCommandException;
import Task.Task;
import Task.Deadline;
import Task.Events;
import Task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String MISSING_TIME_PERIOD = "Please enter a time period for the task!";
    public static final String ITEM_OUT_OF_RANGE = "Item requested is out of range! Try another item.";
    public static final String INVALID_INDEX = "Invalid index! Please enter a valid integer as index!";

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
                try {
                    printListOfTasks(myList);
                } catch (EmptyListException e) {
                    System.out.println(e);
                }
                break;
            case "done":
                try {
                    markTaskAsDone(myList, commands, userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ITEM_OUT_OF_RANGE);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INDEX);
                } catch (MissingItemIndexException e) {
                    System.out.println(e);
                }
                break;
            case "delete":
                try{
                    deleteTask(myList, commands, userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ITEM_OUT_OF_RANGE);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INDEX);
                } catch (MissingItemIndexException e) {
                    System.out.println(e);
                }
                break;
            case "todo":
                try {
                    addNewToDo(myList, commands, userInput);
                } catch (MissingDescriptionException e) {
                    System.out.println(e);
                }
                break;
            case "deadline":
                try {
                    addNewDeadline(myList, commands, userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(MISSING_TIME_PERIOD);
                } catch (MissingDescriptionException e) {
                    System.out.println(e);
                }
                break;
            case "event":
                try {
                    addNewEvent(myList, commands, userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(MISSING_TIME_PERIOD);
                } catch (MissingDescriptionException e) {
                    System.out.println(e);
                }
                break;
            default:
                try {
                    throw new UnknownCommandException(userInput);
                } catch (UnknownCommandException e) {
                    System.out.println(e);
                }
            }
            printNewLine();
        } while (!userInput.equals("bye"));

        cleanUpAndExit(myScanner);
    }

    public static void addNewDeadline(ArrayList<Task> myList, String[] commands, String userInput)
            throws MissingDescriptionException, IndexOutOfBoundsException {
        if (isInvalidLength(commands)) {
            throw new MissingDescriptionException(userInput);
        } else {
            // parsing userInput, remove first word "deadline"
            String[] deadlineDetails = removeFirstWord(commands).split("/");
            myList.add(new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim()));
            printSuccessfulAddMessage(myList.get(Task.getNumberOfTasksInList() - 1).toString());
        }
    }

    public static void addNewToDo(ArrayList<Task> myList, String[] commands, String userInput)
            throws MissingDescriptionException {
        if (isInvalidLength(commands)) {
            throw new MissingDescriptionException(userInput);
        } else {
            String parsedUserInput = removeFirstWord(commands);
            myList.add(new Todo(parsedUserInput.trim()));
            // gets last newly added Task.Task
            printSuccessfulAddMessage(myList.get(Task.getNumberOfTasksInList() - 1).toString());
        }
    }

    public static void addNewEvent(ArrayList<Task> myList, String[] commands, String userInput)
            throws IndexOutOfBoundsException, MissingDescriptionException{
        if (isInvalidLength(commands)) {
            throw new MissingDescriptionException(userInput);
        } else {
            // parsing userInput, remove first word "event"
            String[] eventDetails = removeFirstWord(commands).split("/");

            // testing IndexOutOfBoundsException
            myList.add(new Events(eventDetails[0].trim(), eventDetails[1].trim()));
            printSuccessfulAddMessage(myList.get(Task.getNumberOfTasksInList() - 1).toString());
        }
    }

    public static void markTaskAsDone(ArrayList<Task> myList, String[] commands, String userInput)
            throws IndexOutOfBoundsException, NumberFormatException, MissingItemIndexException {
        if (isInvalidLength(commands)){
            throw new MissingItemIndexException(userInput);
        }
        // testing NumberFormatException
        int itemIndexRequested = Integer.parseInt(commands[1]) - 1;

        // testing IndexOutOfBoundsException
        myList.get(itemIndexRequested).markAsDone();
        System.out.println(myList.get(itemIndexRequested)
                .getDoneResponseMessage(itemIndexRequested + 1));
    }

    public static void deleteTask(ArrayList<Task> myList, String[] commands, String userInput)
            throws IndexOutOfBoundsException, NumberFormatException, MissingItemIndexException {
        if (isInvalidLength(commands)){
            throw new MissingItemIndexException(userInput);
        }
        // testing NumberFormatException
        int itemIndexRequested = Integer.parseInt(commands[1]) - 1;
        String taskDetails = myList.get(itemIndexRequested).toString();

        // testing IndexOutOfBoundsException
        myList.remove(itemIndexRequested);
        printSuccessfulDeleteMessage(taskDetails);

    }

    public static boolean isInvalidLength(String[] commands) {
        return commands.length < 2;
    }

    public static void printListOfTasks(ArrayList<Task> myList)
            throws EmptyListException {
        if (myList.isEmpty()) {
            // dummy argument
            throw new EmptyListException("Empty list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < myList.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1,
                        myList.get(i).toString()));
            }
        }
    }

    public static void printSuccessfulAddMessage(String taskDetails) {
        System.out.println(String.format("Added the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    public static void printSuccessfulDeleteMessage(String taskDetails) {
        // update number of Tasks left
        Task.reduceNumberOfTaskInList();
        System.out.println(String.format("Removed the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    /**
     * Returns the user input in a String format without the first command
     * @param command the Array of split commands entered
     * @return description of Task.Task as a String
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
