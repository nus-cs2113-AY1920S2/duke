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
        String commandList =
            "Legend:\n"
            + "[Y]: Task is completed\n"
            + "[N]: Task is not completed\n\n"
            + "Commands: "
            + "Exit: Exits program \nUsage: bye\n\n"
            + "List: Lists all recorded tasks \nUsage: list\n\n"
            + "Done: Mark task as completed \nUsage: done <task number>\n\n"
            + "Todo: Basic Tasks without date/time \nUsage: todo <task> \n\n"
            + "Event: Event tasks including date/time \nUsage: event <task> /<YYYY-MM-DD> <HHMM> \n\n"
            + "Deadline: Deadline tasks including date/time \nUsage: deadline <task> /<YYYY-MM-DD> <HHMM> \n\n"
            + "Delete: Deletes task from list \nUsage: delete <task number>\n\n"
            + "Find: Search for task using keyword \nUsage: find <key word>\nnote: Keyword is only one word\n";
        System.out.println(commandList);
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
}
