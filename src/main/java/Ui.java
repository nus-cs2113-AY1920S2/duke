/**
 * Represents a user interface object with various methods
 * that helps Duke interact with the end user.
 */
public class Ui {
    private static final String logo = "  ____        _        \n"
            + " |  _ \\ _   _| | _____ \n"
            + " | | | | | | | |/ / _ \\\n"
            + " | |_| | |_| |   <  __/\n"
            + " |____/ \\__,_|_|\\_\\___|\n";


    /**
     * Method used to print the greeting message when the user
     * launch the application.
     */
    public void showWelcomeMessage() {
        printLine();
        System.out.println(logo);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printLine();
    }

    /**
     * Method used to print a horizontal line so it is easier
     * to differentiate between the user input and program response.
     */
    public void printLine() {
        for (int i = 0; i < 60; i += 1) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Method used to terminate the program and print the exit message.
     */
    public void exitFromApp() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
        System.exit(0);
    }

    /**
     * Method used to print an IO exception message.
     */
    public void showLoadingError() {
        System.out.println("IO Exception occurred");
    }
}
