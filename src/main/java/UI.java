import java.util.Scanner;

/**
 * <h1>UI</h1>
 * This class deals with interactions with the user.
 * It contains functions for general Duke-user interaction
 */
public class UI {
    private static Scanner sc = new Scanner(System.in);

    public UI() {
        printGreeting();
    }

    /**
     * Prints the greeting message when Duke is started
     */
    protected static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints the help message when the user executes a
     * wrong command or if the 'help' command is run
     */
    protected static void printHelp() {
        String helpMsg = "Here is a list of things you can do: \n"
                + "\ttodo:      tasks without a date/time (syntax: todo buy food)\n"
                + "\tlist:      list your current tasks (syntax: list)\n"
                + "\tdeadline:  tasks that need to be done by a date/time (syntax: deadline buy food /by Sunday)\n"
                + "\tevent:     tasks that start/end by a specific time (syntax: event food fair /at Mon 2-4pm)\n"
                + "\tdone x:    mark the xth task as done (syntax: done 3)\n"
                + "\tdelete x:  remove the xth task (syntax: delete 3)\n"
                + "\tfind:      find a task with the given keyword (syntax: find apple)\n"
                + "\thelp:      launch the help screen (syntax: help)";
        System.out.println(helpMsg);
    }

    /**
     * Prepares to receive a user input
     * @return the user input
     */
    protected static String getUserCommand() {
        System.out.println("==========================");
        System.out.println("How can I help you?");
        return sc.nextLine();
    }
}