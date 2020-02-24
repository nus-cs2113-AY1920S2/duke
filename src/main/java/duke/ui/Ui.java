package duke.ui;

import duke.commands.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


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
    
    public Ui() {
        this(System.in, System.out);
    }
    
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    
    public String getUserCommand() {
        out.print(ENTER_COMMAND);
        String inputLine = in.nextLine().trim();
        echoUserInput(inputLine);
        return inputLine;
    }
    
    private void echoUserInput(String userInput) {
        out.println(COMMAND_ENTERED + userInput);
    }
    
    public static void printToConsole(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
    
    public void showWelcomeMessage() {
        printToConsole(DIVIDER, WELCOME_MESSAGE, DIVIDER);
    }
    
    public void showGoodByeMessage() {
        printToConsole(GOODBYE_MESSAGE, DIVIDER);
    }
    
    public void showLoadMessage() {
        printToConsole(LOAD_SAVE_DATA);
    }
    
    public void showResultToUser(CommandResult result) {
        printToConsole(result.feedbackToUser);
    }
    
}

