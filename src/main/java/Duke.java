import java.util.Scanner;


public class Duke {

    /** Scanner to receive input from user */
    private static final Scanner INPUT = new Scanner(System.in);

    /** Printer contains methods to print messages for the user */
    private static Output printer;

    // TODO implement a better way to handle commnds
    /** Command contains the names of each command */
    private static Command command;

    /** Manages the lists of tasks */
    private static TaskManager taskManager;

    /** Flag to tell whether the program should stop or not */
    private static boolean isProgramRunning;


    /**
     *  Prints the tasks that are currently in the list
     */
    public static void executeListTasks () {
        taskManager.listTasks();
    }

    /**
     * Executes the add deadline command by checking for the correct
     * format
     *
     * @param userInput Raw user input containing the task description and
     *                  by date
     */
    public static void executeAddDeadline (String userInput) {

        int indexOfBy = userInput.indexOf("/by");

        if (indexOfBy == -1) {
            printer.printInvalidDeadline();
        } else {

            String taskDescription = userInput.substring(0, indexOfBy).trim();
            String byDate = userInput.substring(indexOfBy + 3).trim();

            taskManager.addTask(new Deadline(taskDescription, byDate));
        }

    }

    /**
     * Executes the add event command by checking for the correct
     * format
     *
     * @param userInput Raw user input containing task description and
     *                  at date
     */
    public static void executeAddEvent (String userInput) {

        int indexOfBy = userInput.indexOf("/at");

        if (indexOfBy == -1) {
            printer.printInvalidEvent();
        } else {

            String taskDescription = userInput.substring(0, indexOfBy).trim();
            String eventDate = userInput.substring(indexOfBy + 3).trim();

            taskManager.addTask(new Event(taskDescription, eventDate));
        }

    }

    /**
     * Executes the to do command and adds the corresponding task
     * to the list
     *
     * @param userInput Raw user input containing the task description
     */
    public static void executeAddTodo (String userInput) {

        String taskDescription = userInput.trim();

        taskManager.addTask(new Todo(taskDescription));
    }

    /**
     * Processes user input and marks the task as done
     *
     * @param userInput Raw user input containing the index of the task
     *                  to mark as done
     */
    public static void executeDone (String userInput) {
        int taskIndex = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

        taskManager.markTaskAsDone(taskIndex);
    }

    /**
     * Prints new lines to make it look like the command window was cleared
     */
    private static void executeClearWindow () {
        int newLinesToPrint = 100;

        for (int i = 0; i < newLinesToPrint; i++) {
            System.out.println();
        }
    }

    /**
     * Prints the descriptions on how to use each one of the supported commands
     */
    private static void executeHelp() {
        printer.printHelp();
    }


    private static void executeSpotBug () {
        printer.printEasterEgg();
    }


    /**
     * Checks the type of command received, if any. Redirects
     * path to specific execute function to process the user's input
     *
     * @param userResponse Raw input as entered by the user
     */
    private static void processUserInput(String userResponse) {

        String cmd = userResponse.split(" ")[0];
        userResponse = userResponse.replace(cmd, "");

        if (cmd.equals(command.CMD_EXIT)) {
            isProgramRunning = false;
            return;

        } else if (cmd.equals(command.CMD_HELP)) {
            executeHelp();

        } else if (cmd.equals(command.CMD_LIST)) {
            executeListTasks();

        } else if (cmd.equals(command.CMD_DONE)) {
            executeDone(userResponse);

        } else if (cmd.equals(command.CMD_ADD_DEADLINE)) {
            executeAddDeadline(userResponse);

        } else if (cmd.equals(command.CMD_ADD_EVENT)) {
            executeAddEvent(userResponse);

        } else if (cmd.equals(command.CMD_ADD_TODO)) {
            executeAddTodo(userResponse);

        } else if (cmd.equals(command.CMD_CLEAR_WINDOW)) {
            executeClearWindow();
        }
        else if (cmd.equals(command.CMD_DEBUG)) {
            executeSpotBug();
        } else {
            printer.printInvalidCommand();
        }

        return;
    }

    /**
     * Gets the command from the user and handles it so that it is
     * ready for processing
     *
     * @return input given by the user
     */
    public static String getUserInput () {

        String userInput = INPUT.nextLine();
        return userInput.trim();
    }


    /**
     * Initializes objects to handle the list, display output, and for
     * command information
     */
    public static void init () {
        taskManager = new TaskManager();
        printer = new Output();
        command = new Command();

        isProgramRunning = true;
    }


    public static void main (String[] args) {

        init();

        printer.greetUser();

        String userResponse;

        while (isProgramRunning) {

            userResponse = getUserInput();
            processUserInput(userResponse);

        }

        printer.displayFarewell();
    }

}
