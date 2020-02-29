package duke.ui;

import duke.command.CommandResult;

public class Ui {

    public static void greetUser(String[] args) {

        if (args.length >= 1 && args[0].equals("1")) {
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
     * Prints logo for the bot.
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
     * Repeats whatever message it receives.
     *
     * @param msg Message to print.
     */
    public static void displayMessage(String msg) {

        printHorizontalLine(false);
        System.out.println("\t" + msg);
        printHorizontalLine(true);
    }

    /**
     * Prints horizontal line for chat bot.
     */
    private static void printHorizontalLine(boolean hasNewline) {
        System.out.println("  _______________________________________________________________________________");

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


    public void displayFeedback(CommandResult userFeedback) {

        String feedback = userFeedback.getUserFeedback();

        if (!feedback.isEmpty()) {
            displayMessage(feedback);
        }

    }
}
