package duke.ui;

import duke.command.CommandResult;

import static duke.Duke.inDebugMode;

/**
 * A class representing the Text UI application
 */
public class Ui {

    /**
     * Displays welcome message.
     */
    public static void greetUser() {

        if (inDebugMode) {
            return;
        }

        String name = "Zapato";

        printHorizontalLine(false);
        printLogo();

        System.out.println("\tHello from " + name + ":)");
        System.out.println("\tWhat can I do for you? (type \"help\" for more info)");
        printHorizontalLine(true);

    }

    /**
     * Displays application logo.
     */
    private static void printLogo() {

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
     * Repeats whatever message it receives but formatted.
     *
     * @param msg Message to print.
     */
    public static void displayMessage(String msg) {

        printHorizontalLine(false);
        System.out.println("\t" + msg);
        printHorizontalLine(true);
    }

    /**
     * Prints horizontal line.
     */
    private static void printHorizontalLine(boolean hasNewline) {
        System.out.println("  ____________________________________________________________________________________________________");

        if (hasNewline) {
            System.out.println();
        }
    }

    /**
     * Displays farewell message.
     */
    public static void displayFarewell() {
        displayMessage("Bye! Hope to see you soon :)");
    }

    /**
     * Displays the command execution results.
     *
     * @param userFeedback Feedback given by the execution of a command.
     */
    public void displayFeedback(CommandResult userFeedback) {

        String feedback = userFeedback.getUserFeedback();

        if (!feedback.isEmpty()) {
            displayMessage(feedback);
        }

    }
}
