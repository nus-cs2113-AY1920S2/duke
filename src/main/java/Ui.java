import java.util.Scanner;

public class Ui {
    protected String DIVIDER = "____________________________________________________________";
    protected String logo = " ________     _____     _____     __\n"
            + "|_____   |  /  ___  \\  |     \\   |  | \n"
            + "      |  | |  /   \\  | |  |\\  \\  |  |\n"
            + " __   |  | |  |___|  | |  | \\  \\ |  |\n"
            + "|  |__|  | |   ___   | |  |  \\  \\|  |\n"
            + "|________/ |__|   |__| |__|   \\_____|\n";

    /**
     * Print line as a divider between each command and results
     */
    public void printDivider(){
        System.out.println(DIVIDER);
    }

    /**
     * Display welcome message
     */
    public void printGreetingMessage() {
        System.out.println(logo);
        printDivider();
        System.out.println("Hello! I'm Jan\n" + " What can I do for you?");
        printDivider();
    }

    /**
     * Display program terminating message.
     */
    public void printGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Display general error message if exception is not due to user
     * @param errorMsg
     */
    public void printGeneralErrorMessage(String errorMsg) {
        System.out.println("Something went wrong: " + errorMsg);
    }

    /**
     * Display message if command has details but cannot be parsed, likely due to incorrect
     * command format
     */
    public void printIncorrectFormatMessage() {
        System.out.println("Invalid request possibly due to incorrect format. type \"help\" to find out more");
    }

    /**
     * Display help message with all commands that can be executed
     */
    public void printHelpMessage() {
        System.out.println("Try using the following commands:\n"
                + "list                             -- to get a list of all the existing tasks\n"
                + "done [item number]               -- mark task as completed\n"
                + "todo [item]                      -- add new todo task\n"
                + "deadline [item] /by [date][time] -- add new deadline task\n"
                + "event [item] /at [date][time]    -- add new event task\n"
                + "bye                              -- exit program");
    }

    /**
     * Display message if task with specified task number does not exist.
     */
    public void printTaskNotFoundMessage() {
        System.out.println("Jan cannot find this task. Type \"list\" to see all the tasks you have.");
    }

    /**
     * Take in user input as full Command string for parsing.
     * @return a String that contains all information of the command
     */
    public static String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

}
