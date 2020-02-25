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

    /** Prints line divider */
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

    public static void printAddedTaskMessage(Task t, int listSize) {
        UI.br();
        System.out.println("\t Dook has added task: ");
        System.out.println("\t  " + t);
        System.out.println("\t " + listSize + " task(s) in the list now!");
        UI.br();
    }

    /** Prints the message shown when there is an IO exception */
    public static void showLoadingError() {
        br();
        System.out.println("\t You did not have an old task list! Creating new one...");
        br();
    }

    public static void showInvalidCommandError() {
        UI.br();
        System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :(");
        UI.br();
    }

    public static void showMissingDescriptionerror() {
        UI.br();
        System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :(");
        UI.br();
    }

    public static void showInvalidTaskError() {
        UI.br();
        System.out.println("\t This task doesn't exist!");
        UI.br();
    }
}
