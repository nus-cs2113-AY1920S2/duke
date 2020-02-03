import java.util.Scanner;

public class Duke {

    /** Maximum number of tasks */
    private static final int MAX_TASK_NUM = 100;


    /** Number of tasks in the list */
    private static Task[] tasks = new Task[MAX_TASK_NUM];

    /** Counter of how many tasks there are */
    private static int taskCounter = 0;

    /** Scanner to receive input from user */
    private static final Scanner INPUT = new Scanner(System.in);

    /** User commands available to the user */
    private static final String CMD_ADD_TODO = "todo";
    private static final String CMD_ADD_DEADLINE = "deadline";
    private static final String CMD_ADD_EVENT = "event";
    private static final String CMD_DONE = "done";
    private static final String CMD_LIST = "list";
    private static final String CMD_EXIT = "bye";
    private static final String CMD_HELP = "help";
    private static final String CMD_CLEAR_WINDOW = "clear";

    /** Easter egg */
    private static final String CMD_DEBUG = "debug";

    /** Flag to check whether the main program should be running or not */
    private static boolean isProgramRunning = true;


    /**
     * Prints horizontal line for chat bot.
     */
    public static void printLine (boolean hasNewline) {
        System.out.println("  _____________________________________________________________________");

        if (hasNewline) {
            System.out.println();
        }
    }

    /**
     * Prints logo for the bot.
     */
    public static void printLogo () {

        String logo = "\t ________  ________  ________  ________  _________  ________     \n" +
                "\t|\\_____  \\|\\   __  \\|\\   __  \\|\\   __  \\|\\___   ___\\\\   __  \\    \n" +
                "\t \\|___/  /\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\|___ \\  \\_\\ \\  \\|\\  \\   \n" +
                "\t     /  / /\\ \\   __  \\ \\   ____\\ \\   __  \\   \\ \\  \\ \\ \\  \\\\\\  \\  \n" +
                "\t    /  /_/__\\ \\  \\ \\  \\ \\  \\___|\\ \\  \\ \\  \\   \\ \\  \\ \\ \\  \\\\\\  \\ \n" +
                "\t   |\\________\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\__\\   \\ \\__\\ \\ \\_______\\\n" +
                "\t    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|__|    \\|__|  \\|_______|\n" +
                "\t                                                                 ";

        System.out.println(logo + "\n");
    }

    /**
     * Greets user.
     */
    public static void greetUser () {
        String name = "Zapato";

        printLine(false);
        printLogo();

        System.out.println("\tHello from " + name + ":)");
        System.out.println("\tWhat can I do for you? (type \"help\" for more info)");
        printLine(true);
    }

    /**
     * Displays farewell message.
     */
    public static void displayFarewell () {
        printLine(false);
        System.out.println("\tBye. Hope to see you soon :)!");
        printLine(true);
    }

    /**
     * Repeats whatever message it receives.
     *
     * @param msg Message to print.
     */
    public static void replayBack (String msg) {
        printLine(false);
        System.out.println("\t" + msg);
        printLine(true);
    }

    /**
     * Adds task to the tasks array.
     *
     * @param task Task to add to the list.
     */
    public static void addTask (Task task) {
        if (taskCounter < MAX_TASK_NUM) {
            tasks[taskCounter++] = task;

            String msg = "Okay! I have added the following task to your list: ";
            msg += System.lineSeparator() + "\t\t\u2023" + task;
            msg += System.lineSeparator() + "\tNow you have " + (taskCounter) + " tasks in your list :)";

            replayBack(msg);
        }
        
    }

    /**
     * Marks the specific task as done if constraints are met.
     *
     * @param taskIndex The task index in the array. Must be within
     *                  the range of available tasks.
     */
    public static void markTaskAsDone (int taskIndex) {

        String msg;
        if (taskIndex <= 0 || taskCounter == 0) {
            msg = "Well...I cannot mark something that doesn't exist as done >:(";

        } else if (taskIndex > taskCounter) {
            msg = "Sorry, but you only have " + taskCounter + " tasks :'(";

        } else {

            if (!tasks[taskIndex - 1].getCompletionStatus()) {
                tasks[taskIndex - 1].setTaskAsDone();

                msg = "Okay! Marked this task as done :) :" + System.lineSeparator() + "\t\t" +
                        tasks[taskIndex - 1];

            } else {
                msg = "The task: " + System.lineSeparator() + "\t\t\u2023 " + tasks[taskIndex - 1] +
                        "\t" + "has already been marked as done before";
            }
        }

        replayBack(msg);
    }


    /**
     *  Prints the tasks that are currently in the list
     */
    public static void executeListTasks () {

        if (taskCounter == 0) {
            replayBack("List is empty");
            return;
        }

        String msg = "Here is your list so far: " + System.lineSeparator() + System.lineSeparator();
        for ( int i = 0; i < taskCounter; i++ ) {

            if ( i == taskCounter - 1 ) {
                msg += "\t" + (i + 1) + "." + tasks[i];
                continue;
            }
            msg += "\t" + (i + 1) + "." + tasks[i] + System.lineSeparator();

        }
        replayBack(msg);
    }

    /**
     * Executes the add deadline command by checking for the correct
     * format
     *
     * @param userInput Raw user input containing the task description and
     *                  by date
     */
    public static void executeAddDeadline (String userInput) {

        String message = "";
        int indexOfBy = userInput.indexOf("/by");

        if (indexOfBy == -1) {
            message = "Invalid input for adding a deadline. Type help for more information";
        } else {

            String taskDescription = userInput.substring(0, indexOfBy).trim();
            String byDate = userInput.substring(indexOfBy + 3).trim();

            addTask(new Deadline(taskDescription, byDate));
        }

        if (message.length() > 0) {
            replayBack(message);
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

        String message = "";
        int indexOfBy = userInput.indexOf("/at");

        if (indexOfBy == -1) {
            message = "Invalid input for adding an event. Type help for more information";
        } else {

            String taskDescription = userInput.substring(0, indexOfBy).trim();
            String eventDate = userInput.substring(indexOfBy + 3).trim();

            addTask(new Event(taskDescription, eventDate));
        }

        if (message.length() > 0) {
            replayBack(message);
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

        addTask(new Todo(taskDescription));
    }

    /**
     * Processes user input and marks the task as done
     *
     * @param userInput Raw user input containing the index of the task
     *                  to mark as done
     */
    public static void executeDone (String userInput) {
        int taskIndex = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
        markTaskAsDone(taskIndex);
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
        String msg = "Below are descriptions of the supported commands:" + System.lineSeparator();

        // help for exit command
        msg += System.lineSeparator() + String.format("\t%s: exits the program. " +
                "Input must follow the format below,", CMD_EXIT);
        msg += System.lineSeparator() + "\t\tbye";
        msg += System.lineSeparator();

        // help for done command
        msg += System.lineSeparator() + String.format("\t%s: marks task as done. " +
                "Input must follow the format below,", CMD_DONE);
        msg += System.lineSeparator() + "\t\tdone [task number]";
        msg += System.lineSeparator();

        // help for done command
        msg += System.lineSeparator() + String.format("\t%s: lists the tasks currently available. " +
                "Input must follow the format below,", CMD_LIST);
        msg += System.lineSeparator() + "\t\tlist";
        msg += System.lineSeparator();

        // help for deadline command
        msg += System.lineSeparator() + String.format("\t%s: adds deadline to your list of tasks. " +
                "Input must follow the format below,", CMD_ADD_DEADLINE);
        msg += System.lineSeparator() + "\t\tdeadline [description] /by [date/time]";
        msg += System.lineSeparator();

        // help for event command
        msg += System.lineSeparator() + String.format("\t%s: adds event to your list of tasks. Input " +
                "must follow the format below,", CMD_ADD_EVENT);
        msg += System.lineSeparator() + "\t\tevent [description] /at [date/time]";
        msg += System.lineSeparator();

        // help for to do command
        msg += System.lineSeparator() + String.format("\t%s: add todo to your list of tasks. Input" +
                " must follow the format below,", CMD_ADD_TODO);
        msg += System.lineSeparator() + "\t\ttodo [description]";
        msg += System.lineSeparator();

        // help for clear window
        msg += System.lineSeparator() + String.format("\t%s: clears command window. Input" +
                " must follow the format below,", CMD_CLEAR_WINDOW);
        msg += System.lineSeparator() + "\t\tclear";
        msg += System.lineSeparator();

        replayBack(msg);
    }


    // ASCII art from: https://www.asciiart.eu/computers/bug, by Joan Start
    private static void executeSpotBug () {

        String msg = "\tNice! you found a bug :) keep it up!" + System.lineSeparator() + System.lineSeparator();

        msg += "\t    .--.       .--.\n" +
                "\t    _  `    \\     /    `  _\n" +
                "\t     `\\.===. \\.^./ .===./`\n" +
                "\t            \\/`\"`\\/\n" +
                "\t         ,  | y2k |  ,\n" +
                "\t        / `\\|;-.-'|/` \\\n" +
                "\t       /    |::\\  |    \\\n" +
                "\t    .-' ,-'`|:::; |`'-, '-.\n" +
                "\t        |   |::::\\|   | \n" +
                "\t        |   |::::;|   |\n" +
                "\t        |   \\:::://   |\n" +
                "\t        |    `.://'   |\n" +
                "\tjgs    .'             `.\n" +
                "\t    _,'                 `,_";

        replayBack(msg);
    }


    /**
     * Checks the type of command received, if any. Redirects
     * path to specific execute function to process the user's input
     *
     * @param userResponse Raw input as entered by the user
     */
    private static void processUserInput(String userResponse) {

        String command = userResponse.split(" ")[0];
        userResponse = userResponse.replace(command, "");

        if (command.equals(CMD_EXIT)) {
            isProgramRunning = false;
            return;

        } else if (command.equals(CMD_HELP)) {
            executeHelp();

        } else if (command.equals(CMD_LIST)) {
            executeListTasks();

        } else if (command.equals(CMD_DONE)) {
            executeDone(userResponse);

        } else if (command.equals(CMD_ADD_DEADLINE)) {
            executeAddDeadline(userResponse);

        } else if (command.equals(CMD_ADD_EVENT)) {
            executeAddEvent(userResponse);

        } else if (command.equals(CMD_ADD_TODO)) {
            executeAddTodo(userResponse);

        } else if (command.equals(CMD_CLEAR_WINDOW)) {
            executeClearWindow();
        }
        else if (command.equals(CMD_DEBUG)) {
            executeSpotBug();
        } else {
            replayBack("Invalid command. Please try again");
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


    public static void main (String[] args) {

        greetUser();

        String userResponse;
        String result;

        while (isProgramRunning) {

            userResponse = getUserInput();
            processUserInput(userResponse);

        }

        displayFarewell();
    }

}
