package ui;

public class Ui {

    /**
     * Print the welcome message when Duke is started.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Print the help manual when user inputs the "help" command.
     */
    public void printHelp() {
        System.out.println("Duke supports the following commands");
        System.out.println("Please enter the keyword followed by details required in the <>");
        System.out.println("todo <task details> --------------------------- Create a new task");
        System.out.println("deadline <task details> /by: <timing details> - Create a new task with a time element");
        System.out.println("event <event details> /at: <timing details> --- Create a new event with a time element");
        System.out.println("done <task number> ---------------------------- Mark task as done");
        System.out.println("delete <task number> -------------------------- Delete task");
        System.out.println("fine <task details> --------------------------- "
                + "Display a list of tasks containing the keyword");
        System.out.println("list ------------------------------------------ List all available tasks and events");
        System.out.println("save ------------------------------------------ Save the current list of tasks");
        System.out.println("bye ------------------------------------------- Shutdown Duke\n");
    }

    /**
     * Print a page break.
     */
    public static void printPageBreak() {
        System.out.println("===================================================================================="
                + "=================");
    }
}
