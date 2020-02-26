package ui;

import java.util.Scanner;
import task.Task;

public class UI {
    protected static Scanner input;

    public static void initUI() {
        input = new Scanner(System.in);
    }

    public static String readCommand() {
        return input.nextLine();
    }

    /**
     * Prints the line divider.
     */
    public static void br () {
        System.out.println("    ...................................................");
    }

    public static void printGreetMessage() {
        br();
        String logo =
                "        ┌┬┐┌─┐┌─┐┬┌─\n" +
                        "         │││ ││ │├┴┐\n" +
                        "        ─┴┘└─┘└─┘┴ ┴";
        System.out.println("\t Hello! I am \n" + logo);
        System.out.println("\t Does the human have a request today?");
        br();
    }

    public static void printEndMessage() {
        br();
        System.out.println("\t Goodbye, see you in the seventh dimension!");
        System.out.println("                   *       +\n" +
                "             '                  |\n" +
                "         ()    .-.,=\"``\"=.    - o -\n" +
                "               '=/_       \\     |\n" +
                "            *   |  '=._    |\n" +
                "                 \\     `=./`,        '\n" +
                "              .   '=.__.=' `='      *\n" +
                "     +                         +\n" +
                "          O      *        '       .");
        br();
    }

    /**
     * Prints the message shown after a task is added to the task list.
     *
     * @param t Task that needs to have its details printed.
     * @param listSize Total number of tasks in the task list.
     */
    public static void printAddedTaskMessage(Task t, int listSize) {
        UI.br();
        System.out.println("\t Dook has added task: ");
        System.out.println("\t  " + t);
        System.out.println("\t " + listSize + " task(s) in the list now!");
        UI.br();
    }

    /**
     * Prints the message shown when there is an IO exception.
     */
    public static void showLoadingError() {
        br();
        System.out.println("\t Task list not found! Creating new one...");
        br();
    }

    /**
     * Prints the message shown when an invalid command is entered.
     */
    public static void showInvalidCommandError() {
        UI.br();
        System.out.println("\t Dook does not recognise this command! :(");
        UI.br();
    }

    /**
     * Prints the message shown when part(s) of the description of a task is missing.
     */
    public static void showMissingDescriptionError() {
        UI.br();
        System.out.println("\t Please enter the full task description!");
        UI.br();
    }
}
