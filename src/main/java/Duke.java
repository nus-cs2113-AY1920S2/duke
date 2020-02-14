import java.util.Scanner;

public class Duke {
    private static final String STANDARD_SEPARATOR = "____________________________________________________________";
    private static final String SHANNON = "Shannon";
    private static Task[] listOfTasks = new Task[100]; // array of tasks that is <=100
    private static int sizeOfList = 0; // number of items in the list

    public static void main(String[] args) {
        printWelcomeMessage();

        // Reads in the first command from the user
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        String inputCommand = inputLine.substring(0, 3);

        while (!inputCommand.contains("bye")) {
            if (inputCommand.contains("lis")) {
                // prints out the list
                printsOutTheList();

            } else if (inputCommand.contains("don")) {
                // marks task in the stated index as done
                indicateTaskAsDone(inputLine);

            } else {
                // adds a new task into the list
                addInNewTask(inputLine);

            }
            // take in the next command
            inputLine = scanner.nextLine();
            inputCommand = inputLine.substring(0, 3);
        }

        printExitMessage();
    }



    /**
     * Prints the exit message
     */
    private static void printExitMessage() {
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(STANDARD_SEPARATOR);
    }



    /**
     * Differentiates task and adds into array
     *
     * @param inputLine name of the task to add
     */
    private static void addInNewTask(String inputLine) {

        String inputCommand = inputLine.substring(0, 6);

        if (inputCommand.contains("todo")) {
            addToDo(inputLine);

        } else if (inputCommand.contains("dead")) {
            addDeadline(inputLine);

        } else {
            addEvent(inputLine);

        }

        // creates new task
        // informs user that task has been added to list
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + listOfTasks[sizeOfList-1].toString());
        System.out.println(" Now you have " + sizeOfList + " tasks in the list.");
        System.out.println(STANDARD_SEPARATOR);
    }



    /**
     * Adds in new Event to the array,
     *
     * @param inputLine name of the task to add
     */
    private static void addEvent(String inputLine) {
        String description;
        int indexOfEvent = inputLine.indexOf("/at") + 4;
        int descriptionStart = 6;
        int descriptionEnd = inputLine.indexOf(" /at");

        description = inputLine.substring(descriptionStart, descriptionEnd);
        String at = inputLine.substring(indexOfEvent);
        Event newEvent = new Event(description, at);
        listOfTasks[sizeOfList] = newEvent;
        sizeOfList++;
    }



    /**
     * Adds in new Deadline to the array,
     *
     * @param inputLine name of the task to add
     */
    private static void addDeadline(String inputLine) {
        String description;
        int indexOfDeadline = inputLine.indexOf("/by") + 4;
        int descriptionStart = 9;
        int descriptionEnd = inputLine.indexOf(" /by");

        description = inputLine.substring(descriptionStart, descriptionEnd);
        String by = inputLine.substring(indexOfDeadline);
        Deadline newDeadline = new Deadline(description, by);
        listOfTasks[sizeOfList] = newDeadline;
        sizeOfList++;
    }



    /**
     * Adds in new Todo to array,
     *
     * @param inputLine name of the task to add
     */
    private static void addToDo(String inputLine) {
        String description = inputLine.substring(5);
        Todo newToDo = new Todo(description);
        listOfTasks[sizeOfList] = newToDo;
        sizeOfList++;
    }


    /**
     * Prints acknowledgment that the specified task is done,
     * sets the task in the array to be done
     *
     * @param inputLine name of task to set as done
     */
    private static void indicateTaskAsDone(String inputLine) {
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Nice! I've marked this task as done:");
        // obtain index of task to set as done
        String indexInString = inputLine.substring(5);
        int index = Integer.parseInt(indexInString) - 1;
        // mark as done
        listOfTasks[index].markAsDone();
        System.out.println("   " + listOfTasks[index].toString());
        System.out.println(STANDARD_SEPARATOR);
    }



    /**
     * Prints out the entire list
     *
     */
    private static void printsOutTheList() {
        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < sizeOfList; i++) {
            int k = i + 1;
            System.out.println(" " + k + "." + listOfTasks[i].toString());
        }
        System.out.println(STANDARD_SEPARATOR);
    }



    /**
     * Prints out the welcome message
     */
    private static void printWelcomeMessage() {
        System.out.println("Hello from " + SHANNON);

        System.out.println(STANDARD_SEPARATOR);
        System.out.println(" Hello! I'm " + SHANNON + System.lineSeparator() + " What can I do for you?");
        System.out.println(STANDARD_SEPARATOR);
    }
}
