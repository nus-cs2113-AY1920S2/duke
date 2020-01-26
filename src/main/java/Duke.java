import java.util.Scanner;

public class Duke {

    /** Number of tasks in the list **/
    static String[] tasks = new String[100];

    /** Counter of how many tasks there are **/
    static int taskCounter = 0;


    /**
     * Prints horizontal line for chat bot
     */
    public static void printLine (boolean hasNewline) {
        System.out.println("  ________________________________________________________________");

        if (hasNewline) {
            System.out.println();
        }
    }

    /**
     * Prints logo for the bot
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
     * Greets user
     */
    public static void greetUser () {
        String name = "Zapato";

        printLine(false);
        printLogo();

        System.out.println("\tHello from " + name + ":)");
        System.out.println("\tWhat can I do for you?");
        printLine(true);
    }

    /**
     * Displays farewell message
     */
    public static void displayFarewell () {
        printLine(false);
        System.out.println("\tBye. Hope to see you soon :)!");
        printLine(true);
    }

    /**
     * Repeats whatever message it receives
     *
     * @param msg Message to print
     */
    public static void replayBack (String msg) {
        printLine(false);
        System.out.println("\t" + msg);
        printLine(true);
    }

    /**
     * Adds task to the tasks array
     *
     * @param task Task to add to the list
     */
    public static void addTask (String task) {
        if (taskCounter < 100) {
            tasks[taskCounter++] = task;

            String msg = "added: " + task;
            replayBack(msg);
        }
    }


    /**
     *  Prints the tasks that are currently in the list
     */
    public static void printTaskList () {
        String msg = "";
        for ( int i = 0; i < taskCounter; i++ ) {

            if ( i == taskCounter - 1 ) {
                msg += (i + 1) + ". " + tasks[i];
                continue;
            }
            msg += (i + 1) + ". " + tasks[i] + System.lineSeparator() + "\t";
        }
        replayBack(msg);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        greetUser();

        String userResponse = "";

        while (!userResponse.equals("bye")) {
            userResponse = input.nextLine();

            if (userResponse.equals("list")) {
                printTaskList();
            } else if (!userResponse.equals("bye")) {
                addTask(userResponse);
            }

        }

        displayFarewell();
    }
}
