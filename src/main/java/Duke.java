import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        // Create Scanner object
        Scanner myScanner = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();
        
        // Create Hello message
        System.out.println("Hello from\n" + LOGO + "What can I do for you?\n");

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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < myList.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1,
                            myList.get(i).getDescriptionInListFormat()));
                }
                break;
            case "done":
                // bounds checking
                if (commands.length < 2) {
                    System.out.println("Enter an index to be marked done!");
                } else if (Integer.parseInt(commands[1]) > myList.size() ||
                        Integer.parseInt(commands[1]) <= 0) {
                    System.out.println("Item requested is out of range! Try another item.");
                } else {
                    int itemIndexRequested = Integer.parseInt(commands[1]) - 1;

                    myList.get(itemIndexRequested).markAsDone();
                    System.out.println(myList.get(itemIndexRequested)
                            .getDoneResponseMessage(itemIndexRequested + 1));
                }
                break;
            case "todo":
                if (commands.length < 2) {
                    System.out.println("Enter an item to be added");
                } else {
                    // parsing userInput, remove first word "todo"
                    String parsedUserInput = removeFirstWordOfUserInputAndStringify(commands);
                    myList.add(new Todo(parsedUserInput.trim()));
                    printSuccessfulAddedMessage(myList.get(Task.getNumberOfTasksInList() - 1)
                            .getDescriptionInListFormat());
                    break;
                }
            case "deadline":
                if (commands.length < 2) {
                    System.out.println("Enter an item to be added");
                } else {
                    // parsing userInput, remove first word "deadline"
                    String stringifyUserInput = removeFirstWordOfUserInputAndStringify(commands);
                    String[] deadlineDetails;

                    // check for deadline
                    if (stringifyUserInput.contains("/")) {
                        deadlineDetails = stringifyUserInput.split("/");
                        myList.add(new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim()));
                        printSuccessfulAddedMessage(myList.get(Task.getNumberOfTasksInList() - 1)
                                .getDescriptionInListFormat());
                    } else {
                        System.out.println("Please enter a deadline for the task!");
                    }
                    break;
                }
            case "event":
                if (commands.length < 2) {
                    System.out.println("Enter an item to be added");
                } else {
                    // parsing userInput, remove first word "event"
                    String stringifyUserInput = removeFirstWordOfUserInputAndStringify(commands);
                    String[] eventDetails;

                    // check for time period
                    if (stringifyUserInput.contains("/")) {
                        eventDetails = stringifyUserInput.split("/");
                        myList.add(new Events(eventDetails[0].trim(), eventDetails[1].trim()));
                        printSuccessfulAddedMessage(myList.get(Task.getNumberOfTasksInList() - 1)
                                .getDescriptionInListFormat());
                    }else {
                        System.out.println("Please enter a time period for the task!");
                    }
                    break;
                }
            default:
                // add Task to myList - pre-Level 4
                myList.add(new Task(userInput.trim()));
                printSuccessfulAddedMessage(myList.get(Task.getNumberOfTasksInList() - 1)
                        .getDescriptionInListFormat());
            }
            printNewLine();
        } while (!userInput.equals("bye"));

        System.out.println("Exiting DUKE\n" + LOGO);
        myScanner.close();
    }

    public static void printSuccessfulAddedMessage(String taskDetails) {
        System.out.println(String.format("Added the task:\n    %s", taskDetails));
        System.out.println(String.format("Now you have %d tasks in the list!",
                Task.getNumberOfTasksInList()));
    }

    /**
     * Returns the user input in a String format without the first command
     * @param command the Array of split commands entered
     * @return description of Task as a String
     */
    private static String removeFirstWordOfUserInputAndStringify(String[] command) {
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
}
