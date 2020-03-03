package duke.ui;

import java.util.Scanner;

import static duke.constant.Constant.*;


/**
 * Ui class to interact with users by giving some informed sentences
 */
public class Ui {
    /**
     * Empty Ui constructor
     */
    public Ui() {

    }

    /**
     * Method to print the welcome message for user
     */
    public void showWelcome() {
        String greeting = DIVIDER + HELLO_MESSAGE + HELP_MESSAGE + DIVIDER;
        System.out.println(greeting);
    }

    /**
     * Method to print divider for user
     */
    public void showLine() {
        System.out.print(DIVIDER);
    }

    /**
     * Method to print an empty next line
     */
    public void showNextLine() {
        System.out.println("");
    }

    /**
     * Method to read command from the user and pass it back to Parser for dealing
     * with it
     *
     * @return command command string that included user's input
     */
    public String readCommand() {
        Scanner commandScanner = new Scanner(System.in);
        String command = commandScanner.nextLine();
        return command;
    }

    /**
     * Method to print the error that the file has not been created
     */
    public void showLoadingError() {
        System.out.println(DIVIDER + "\tThe directory does not exist, currently creating a new one"
        + DIVIDER);
    }

    /**
     * Method to print the bye message to user
     */
    public void showBye() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Method to print errors encountered by user when he/she inputted something
     * in a wrong way
     * @param errorMessage error message delivered by the exception
     * @return error message string
     */
    public String showError(String errorMessage) {
        return DIVIDER
                + ("☹ OOPS!!! I'm sorry, but there is an error :-(")
                + (errorMessage)
                + DIVIDER;
    }
}
