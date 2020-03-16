package duke;

import java.util.Scanner;

/**
 * The Ui class is an Object that deals with interaction with the user.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Empty constructor for Ui.
     */
    public Ui() {
    }

    /**
     * Scan the user input into a String and return it.
     * @return User input.
     */
    public String getUserCommand() {
        String userCommand;
        System.out.println();
        userCommand = sc.nextLine().strip();
        System.out.println("    ____________________________________________________________");
        return userCommand;
    }

    /**
     * Print the Welcome message and call commandList method to List out all the possible command
     * the user can execute.
     */
    public void greetUser() {
        showLine();
        System.out.println("    Hello! I'm duke.Duke");
        printAvailableCommand();
        System.out.println("    What can I do for you?");
        showLine();
    }

    /**
     * Print a line to separate the input and output.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Print out all the possible command the user can execute.
     */
    public void printAvailableCommand() {
        System.out.println("    Here is the list of commands that are available:");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Index | Input            | Command                            |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| 01    | todo j           | Add a task(j) without dateline     |");
        System.out.println("| 02    | deadline j /by d | Add a task(j) with due date d      |");
        System.out.println("| 03    | event j /at d    | Add a task(j) that start at date d |");
        System.out.println("| 04    | list             | List out all the stored task       |");
        System.out.println("| 05    | done i           | Mark task i as done                |");
        System.out.println("| 06    | delete i         | Delete task i                      |");
        System.out.println("| 07    | find j           | Find all task with description j   |");
        System.out.println("| 08    | help             | List out all the commands          |");
        System.out.println("| 09    | bye              | Terminate the program              |");
        System.out.println("|-------+------------------+------------------------------------|");
        System.out.println("| j - refers to String description of Task                      |");
        System.out.println("| d - refers to date & time of Task in format: YYYY-MM-DD HH:MM |");
        System.out.println("| i - refers to int index of Task                               |");
        System.out.println("+---------------------------------------------------------------+");
    }

    /**
     * Print Error message provided.
     * @param errorMessage Error Message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
