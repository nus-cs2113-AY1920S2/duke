package duke.ui;

import duke.commands.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The user interface of Duke
 */
public class Ui {
    
    public static final String DIVIDER = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String TAB = "\t";
    
    private static final String ENTER_COMMAND = "Enter a command: ";
    private static final String COMMAND_ENTERED = "Command entered: ";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke" + LS + "What can i do for you?";
    private static final String GOODBYE_MESSAGE = TAB + "Bye. Hope to see you again soon!";
    private static final String LOAD_SAVE_DATA = "Saved Data (if any) have been preloaded below:";
    
    private final Scanner in;
    private final PrintStream out;
    
    /**
     * Constructor for Ui
     */
    public Ui() {
        this(System.in, System.out);
    }
    
    /**
     * Constructor for Ui
     *
     * @param in  Represent System.in
     * @param out Represent System.out
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    
    /**
     * Get the user command
     *
     * @return the input line string
     */
    public String getUserCommand() {
        out.print(ENTER_COMMAND);
        String inputLine = in.nextLine().trim();
        echoUserInput(inputLine);
        return inputLine;
    }
    
    /**
     * Echo the user's input
     *
     * @param userInput the string of input to be echoed
     */
    private void echoUserInput(String userInput) {
        out.println(COMMAND_ENTERED + userInput);
    }
    
    /**
     * Variable arguments a syntactic sugar type that allows writing a method that can take in a variable number of
     * argument
     *
     * @param message the argument
     */
    public static void printToConsole(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
    
    /**
     * Display the welcome message during startup
     */
    public void showWelcomeMessage() {
        printToConsole(DIVIDER, WELCOME_MESSAGE, DIVIDER);
    }
    
    /**
     * Display goodbye message when exiting
     */
    public void showGoodByeMessage() {
        printToConsole(GOODBYE_MESSAGE, DIVIDER);
    }
    
    /**
     * Display the loading task message
     */
    public void showLoadMessage() {
        printToConsole(LOAD_SAVE_DATA);
    }
    
    /**
     * Shows the result of the command to the user
     *
     * @param result the CommandResult Object
     */
    public void showResultToUser(CommandResult result) {
        printToConsole(result.feedbackToUser);
    }
    
}
