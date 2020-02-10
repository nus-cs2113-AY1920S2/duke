package duke.ui;

import duke.command.Command;
import duke.task.Task;

import java.util.ArrayList;

public class Output {

    final String bulletArrow = "\t\t\u2023";
    private Command command;

    public Output() {
        command = new Command();
    }

    public void printTaskAdded(int taskCount, Task toPrint) {

        String msg = "Okay! I have added the following task to your list:";
        msg += System.lineSeparator() + bulletArrow + toPrint;
        msg += System.lineSeparator() + "\tNow you have " + (taskCount) + " tasks in your list :)";

        displayMessage(msg);
    }

    public void printMarkedTask(Task toPrint) {
        String msg = "Okay! Marked this task as done :) :" + System.lineSeparator() + "\t\t" +
                toPrint;

        displayMessage(msg);
    }

    public void printTaskAlreadyMarked(Task toPrint) {
        String msg = "The task:" + System.lineSeparator() + "\t\t\u2023 " + toPrint +
                System.lineSeparator() + "\t" + "has already been marked as done before";

        displayMessage(msg);
    }

    public void printListIsEmpty() {
        displayMessage("List is empty");
    }

    public void printList(ArrayList<Task> tasks) {

        String msg = "Here is your list so far:" + System.lineSeparator() + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {

            if (i == tasks.size() - 1) {
                msg += "\t" + (i + 1) + "." + tasks.get(i);
                continue;
            }
            msg += "\t" + (i + 1) + "." + tasks.get(i) + System.lineSeparator();

        }
        displayMessage(msg);
    }

    public void printInvalidDeadline() {
        String message = "Invalid input for adding a deadline. Type help for more information";
        displayMessage(message);
    }

    public void printInvalidEvent() {
        String message = "Invalid input for adding an event. Type help for more information";
        displayMessage(message);
    }

    public void printInvalidCommand() {
        displayMessage("Invalid command. Please try again");
    }

    /**
     * Greets user.
     */
    public static void greetUser() {

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
    public static void printLogo() {

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
    public static void printHorizontalLine(boolean hasNewline) {
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

    public void printHelp() {

        String msg = "Below are descriptions of the supported commands:" + System.lineSeparator();

        // help for exit command
        msg += System.lineSeparator() + String.format("\t%s: exits the program. " +
                "Input must follow the format below,", command.CMD_EXIT);
        msg += System.lineSeparator() + "\t\tbye";
        msg += System.lineSeparator();

        // help for done command
        msg += System.lineSeparator() + String.format("\t%s: marks task as done. " +
                "Input must follow the format below,", command.CMD_DONE);
        msg += System.lineSeparator() + "\t\tdone [task number]";
        msg += System.lineSeparator();

        // help for done command
        msg += System.lineSeparator() + String.format("\t%s: lists the tasks currently available. " +
                "Input must follow the format below,", command.CMD_LIST);
        msg += System.lineSeparator() + "\t\tlist";
        msg += System.lineSeparator();

        // help for deadline command
        msg += System.lineSeparator() + String.format("\t%s: adds deadline to your list of tasks. " +
                "Input must follow the format below,", command.CMD_ADD_DEADLINE);
        msg += System.lineSeparator() + "\t\tdeadline [description] /by [date/time]";
        msg += System.lineSeparator();

        // help for event command
        msg += System.lineSeparator() + String.format("\t%s: adds event to your list of tasks. Input " +
                "must follow the format below,", command.CMD_ADD_EVENT);
        msg += System.lineSeparator() + "\t\tevent [description] /at [date/time]";
        msg += System.lineSeparator();

        // help for to do command
        msg += System.lineSeparator() + String.format("\t%s: add todo to your list of tasks. Input" +
                " must follow the format below,", command.CMD_ADD_TODO);
        msg += System.lineSeparator() + "\t\ttodo [description]";
        msg += System.lineSeparator();

        // help for clear window
        msg += System.lineSeparator() + String.format("\t%s: clears command window. Input" +
                " must follow the format below,", command.CMD_CLEAR_WINDOW);
        msg += System.lineSeparator() + "\t\tclear";
        msg += System.lineSeparator();

        displayMessage(msg);

    }

    // ASCII art from: https://www.asciiart.eu/computers/bug, by Joan Start
    public void printEasterEgg() {
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

        displayMessage(msg);
    }
}
