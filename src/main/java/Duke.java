import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();

        // initialise an array of tasks that is <=100
        //sizeOfList is the number of items in the list
        Task[] listOfTasks = new Task[100];
        int sizeOfList = 0;

        // Reads in the first command from the user
        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();

        while (inputCommand.equals("bye") == false) {

            if (inputCommand.equals("list")) {
                // prints out the list
                printsOutTheList(listOfTasks, sizeOfList);

            } else if (inputCommand.contains("done ") == true) {
                // marks task in the stated index as done
                indicateTaskAsDone(listOfTasks, inputCommand);

            } else {
                // adds a new task into the list
                sizeOfList = addInNewTask(listOfTasks, sizeOfList, inputCommand);

            }
            // take in the next command
            inputCommand = scanner.nextLine();
        }

        printExitMessage();
    }

    /**
     * Prints the exit message
     */
    private static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds in new task to the array,
     * returns the new size of the list
     *
     * @param listOfTasks array of tasks
     * @param sizeOfList number of tasks in list
     * @param inputCommand name of the task to add
     * @return the updated number of tasks in list
     */
    private static int addInNewTask(Task[] listOfTasks, int sizeOfList, String inputCommand) {
        // creates new task
        Task newTask = new Task(inputCommand);
        listOfTasks[sizeOfList] = newTask;
        // informs user that task has been added to list
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + inputCommand);
        System.out.println("____________________________________________________________");
        sizeOfList++;
        return sizeOfList;
    }

    /**
     * Prints acknowledgment that the specified task is done,
     * sets the task in the array to be done
     *
     * @param listOfTasks array of tasks
     * @param inputCommand name of task to set as done
     */
    private static void indicateTaskAsDone(Task[] listOfTasks, String inputCommand) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        // obtain index of task to set as done
        String indexInString = inputCommand.substring(5);
        int index = Integer.parseInt(indexInString) - 1;
        // mark as done
        listOfTasks[index].markAsDone();
        System.out.println("   [" + listOfTasks[index].getStatusIcon()
                           + "] " + listOfTasks[index].description);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the entire list
     *
     * @param listOfTasks array of tasks
     * @param sizeOfList number of tasks in the list
     */
    private static void printsOutTheList(Task[] listOfTasks, int sizeOfList) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < sizeOfList; i++) {
            int k = i + 1;
            System.out.println(" " + k + ".[" + listOfTasks[i].getStatusIcon()
                               + "] " + listOfTasks[i].description);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the welcome message
     */
    private static void printWelcomeMessage() {
        String logo = "Shannon";
        System.out.println("Hello from " + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Shannon" + System.lineSeparator() + " What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
