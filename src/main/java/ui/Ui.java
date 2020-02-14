package ui;

import java.util.Scanner;

import command.CommandResult;

import static misc.Messages.MESSAGE_WELCOME;
import static misc.Messages.MESSAGE_COMMAND_RESULT_FAILURE;
import static misc.Messages.MESSAGE_EXIT;

public class Ui {
    
    private static final String BORDER = "\n_____________________"
            + "__________________________________________________"
            + "____________________________________\n";
    
    private final Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public String getCommand() {
        String readline = sc.nextLine();
        return readline;
    }
    
    public void displayOutputMessage(CommandResult commandResult) {
        String output = commandResult.getCommandOutput();
        displayMessage(output);
    }
        
    public void displayWelcomeMessage() {       
        displayMessage(MESSAGE_WELCOME);
    }
    
    public void displayExitMessage() {
        this.sc.close();
        displayMessage(MESSAGE_EXIT);
    }
    
    public void displayBorder() {
        System.out.println(BORDER);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    
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
