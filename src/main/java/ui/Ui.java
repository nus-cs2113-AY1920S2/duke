package ui;

import java.util.Scanner;

/**
 * Manages user interface of Duke
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    Scanner myScanner;

    /**
     * Constructs Ui object and new Scanner object to accept user input
     */
    public Ui() {
        this.myScanner = new Scanner(System.in);
    }

    /**
     * Prints Welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO + "What can I do for you?\n");
    }

    /**
     * Prints Exit message
     */
    public void printGoodbyeMessage() {
        System.out.println("Exiting DUKE\n" + LOGO);
    }

    /**
     * Prints error Message
     * @param errorMessage String of errorMessage
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints hard disk not found error message
     */
    public void printHardDiskNotFound() {
        System.out.println("No existing stored data file found. Creating new file to store data!");
    }

    /**
     * Prints the prompt message for user to enter input
     */
    public void printPromptMessage() {
        System.out.print("Input a command:");
    }

    /**
     * Reads input from user
     * @return String of user input
     */
    public String readCommand() {
        printPromptMessage();
        return myScanner.nextLine();
    }

    /**
     * Prints the divider between user input and response
     */
    public void printDivider() {
        System.out.println("____________________________________________________________");
    }
}
