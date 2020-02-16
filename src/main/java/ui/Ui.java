package ui;

import static misc.Messages.MESSAGE_WELCOME;
import static misc.Messages.MESSAGE_COMMAND_RESULT_FAILURE;
import static misc.Messages.MESSAGE_EXIT;

import java.util.Scanner;

import command.CommandResult;

/**
 * Encapsulates the user interface of the program.
 * This class is responsible in displaying messages to the user.
 */
public class Ui {
    
    /** A string representation of a border. */
    private static final String BORDER = "\n_____________________"
            + "__________________________________________________"
            + "____________________________________\n";
    
    /** A scanner object to read user input. */
    private final Scanner sc;
    
    /** Constructor of a UI that initializes an internal scanner. */
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public String getCommand() {
        String readline = sc.nextLine();
        return readline;
    }
    
    /** 
     * Displays the result of a command to the user.
     * 
     * @param commandResult
     */
    public void displayOutputMessage(CommandResult commandResult) {
        String output = commandResult.getCommandOutput();
        displayMessage(output);
    }
    
    /** Displays a welcome message when program initialized. */
    public void displayWelcomeMessage() {       
        displayMessage(MESSAGE_WELCOME);
    }
    
    /** Displays an exit message when program exits. */
    public void displayExitMessage() {
        this.sc.close();
        displayMessage(MESSAGE_EXIT);
    }
    
    /** Displays a border to surround the output text shown to the user. */
    public void displayBorder() {
        System.out.println(BORDER);
    }

    /** Displays a message to the user. */
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    /** Displays an error message to the user. */
    public void displayErrorMessage(String message) {
        String output = "";
        output += (MESSAGE_COMMAND_RESULT_FAILURE  
                + "\n."
                + "\n."
                + "\n"
                + message);
        
        System.err.println(output);              
    }
}
