import java.util.Scanner;

public class Ui {
    protected String DIVIDER = "____________________________________________________________";
    protected String logo = " ________     _____     _____     __\n"
            + "|_____   |  /  ___  \\  |     \\   |  | \n"
            + "      |  | |  /   \\  | |  |\\  \\  |  |\n"
            + " __   |  | |  |___|  | |  | \\  \\ |  |\n"
            + "|  |__|  | |   ___   | |  |  \\  \\|  |\n"
            + "|________/ |__|   |__| |__|   \\_____|\n";

    public void printDivider(){
        System.out.println(DIVIDER);
    }

    public void printGreetingMessage() {
        System.out.println(logo);
        printDivider();
        System.out.println("Hello! I'm Jan\n" + " What can I do for you?");
        printDivider();
    }

    public void printGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printGeneralErrorMessage(String errorMsg) {
        System.out.println("Something went wrong: " + errorMsg);
    }

    public void printIncorrectCommandMessage() {
        System.out.println("Invalid request possibly due to missing details. type \"help\" to find out more");
        printDivider();
    }
    public void printIncorrectFormatMessage() {
        System.out.println("Invalid request possibly due to incorrect format. type \"help\" to find out more");
    }

    public void printHelpMessage() {
        System.out.println("Try using the following commands:\n"
                + "list                             -- to get a list of all the existing tasks\n"
                + "done [item number]               -- mark task as completed\n"
                + "todo [item]                      -- add new todo task\n"
                + "deadline [item] /by [date][time] -- add new deadline task\n"
                + "event [item] /at [date][time]    -- add new event task\n"
                + "bye                              -- exit program");
        printDivider();
    }

    public void printTaskNotFoundMessage() {
        System.out.println("Jan cannot find this task. Type \"list\" to see all the tasks you have.");
    }

    public static String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

}
