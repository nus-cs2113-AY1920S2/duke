package ui;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import commands.Command;
import common.exceptions.DukeException;
import data.TaskList;

import static common.Messages.MESSAGE_GREETING;
import static common.Messages.MESSAGE_GOODBYE;
import static common.Messages.line;

public class TextUi {
    private final Scanner in;
    private final PrintStream out;
    
    /**
     * Constructor for TextUi class.
     */
    public TextUi() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }
    
    /**
     * Greets the user and displays the tasks that are due today.
     * Runs every time the user starts the program.
     * 
     * @param tasks Stored list of tasks.
     * @throws DukeException
     * @throws IOException
     */
    public void greet(TaskList tasks) throws DukeException, IOException {
        outputMessage(MESSAGE_GREETING);
        Command startingCommand = new Command("show_upcoming", Optional.of("0"));
        tasks.executeCommand(this, null, startingCommand);
    }
    
    /**
     * Prints a message bidding goodbye to the user.
     */
    public void goodbye() {
        outputMessage(MESSAGE_GOODBYE);
    }
    
    /**
     * Prints the selected message.
     * 
     * @param message Message to be printed.
     */
    public void outputMessage(String... message) {
        out.println(line);
        for (String m : message) {
            out.println(m);
        }
        out.println(line);
    }
    
    /**
     * Reads the next user input.
     * 
     * @return String representation of the next user input.
     */
    public String getUserCommand() {
        return in.nextLine();
    }
}
