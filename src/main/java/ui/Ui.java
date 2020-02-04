package ui;

import java.util.Scanner;

import command.CommandResult;

import static misc.Messages.MESSAGE_WELCOME;
import static misc.Messages.MESSAGE_INVALID_COMMAND;
import static misc.Messages.MESSAGE_EXIT;

public class Ui {
    private static final String BORDER = "_______________________________________________________________________";
    private final Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public String getCommand() {
        String readline = sc.nextLine();
        return readline;
    }
    
    public void displayOutputMessage(CommandResult commandResult) {
       displayMessage(BORDER + ("/n" + commandResult.getCommandOutput() + "/n") + BORDER); 
    }
    
    public void displayErrorMessage() {
        displayMessage(MESSAGE_INVALID_COMMAND);
    }
    
    public void displayWelcomeMessage() {       
        displayMessage(MESSAGE_WELCOME);
    }
    
    public void displayExitMessage() {
        displayMessage(MESSAGE_EXIT);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
