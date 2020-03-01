package UI;

import java.util.Scanner;

/**
 * Text UI of the application
 */
public class Ui {

    /**
     * Prints the list of operations supported
     */
    public static void printHelp() {
        System.out.println("Commands: ");
        System.out.println("List: lists all recorded tasks \nusage: list\n");
        System.out.println("Done: mark task as completed \nusage: done <task number>\n");
        System.out.println("Todo: Tasks without date/time \nUsage: todo <task> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Event: Duke.Event including date/time \nUsage: event <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Deadline: Tasks including date/time \nUsage: deadline <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("");
    }

    public static void showError(String error) {
        System.out.println("Error: " + error);
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prompts user for input
     *
     * @return String containing user command for parsing
     */
    public static String readCommand() {
        System.out.print("> ");
        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();
        return userCommand;
    }

    public static void showWelcome() {
        String logo =   "  ,--,       ,---,   .--.--.       ,---,\n"
                + ",--.'|    ,`--.' |  /  /    '.    '  .' \\\n"
                + "|  | :    |   :  : |  :  /`. /   /  ;    '.\n"
                + ":  : '    :   |  ' ;  |  |--`   :  :       \\\n"
                + "|  ' |    |   :  | |  :  ;_     :  |   /\\   \\\n"
                + "'  | |    '   '  ;  \\  \\    `.  |  :  ' ;.   :\n"
                + "|  | :    |   |  |   `----.   \\ |  |  ;/  \\   \\\n"
                + "'  : |__  '   :  ;   __ \\  \\  | '  :  | \\  \\ ,'\n"
                + "|  | '.'| |   |  '  /  /`--'  / |  |  '  '--'\n"
                + ";  :    ; '   :  | '--'.     /  |  :  :\n"
                + "|  ,   /  ;   |.'    `--'---'   |  | ,'\n"
                + "---`-'   '---'                 `--''\n";

        String welcomeMessage = "\n" + logo + "\nYour Lifestyle Scheduling Assistant\n\n"
                                + "type \"help\" for list of commands\n"
                                + "____________________________________________________________\n"
                                + "Everyday is a sunny day!\n"
                                + "____________________________________________________________\n";

        System.out.println(welcomeMessage);
    }

    public static void showLoadingError() {
        System.out.println("Error loading data file");
    }

}
