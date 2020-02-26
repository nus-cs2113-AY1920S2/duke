package duke;

import java.util.Scanner;

/**
 * Ui class for managing input and output of Duke
 */
public class Ui {

    Scanner sc;

    /**
     * Constructor for Ui.
     * Instantiate new Scanner object for reading in inputs.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the start up logo of Duke.
     */
    public void startMessage() {
        String logo = ".______     ______   .______   \n"
                + "|   _  \\   /  __  \\  |   _  \\  \n"
                + "|  |_)  | |  |  |  | |  |_)  | \n"
                + "|   _  <  |  |  |  | |   _  <  \n"
                + "|  |_)  | |  `--'  | |  |_)  | \n"
                + "|______/   \\______/  |______/  \n";
        String welcomeMessage = "Hello human! I am Bob.\n" + "What can I do for you?";

        System.out.println("Bob the chatbot\n" + logo);
        System.out.println(welcomeMessage);
        System.out.println("____________________________________________________________________");
    }

    /**
     * Reads input from user.
     * @return String containing full input from User
     */
    public String readCommand() {
        return (sc.nextLine());
    }

    /**
     * Prints error messages
     * @param err String containing error message
     */
    public void displayErrorMessage(String err) {
        System.out.println(err);
    }

    /**
     * Prints divider line for Duke.
     */
    public void displayDividerLine() {
        System.out.println("____________________________________________________________________");
    }


}
