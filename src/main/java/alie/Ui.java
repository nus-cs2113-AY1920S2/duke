package alie;

import alie.commands.CommandResult;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Object to manage all input and output of ALIE.
 */
public class Ui {

    public static final String logo =
                      "    /\\       |        |   |‾‾‾‾‾" + System.lineSeparator()
                    + "   /  \\      |        |   |"      + System.lineSeparator()
                    + "  /____\\     |        |   |----"  + System.lineSeparator()
                    + " /      \\    |        |   |"      + System.lineSeparator()
                    + "/        \\ . |_____ . | . |_____ .";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor to initialize value in Object
     */
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints ALIE's welcome message.
     */
    public void showWelcome() {
        out.println("Hello from\n" + logo);
    }

    /**
     * Prints header for ALIE's response.
     */
    public static void printHeader() {
        System.out.print("ALIE> ");
    }

    /**
     * Getter to obtain input from the user
     * @return String containing user's unfiltered input.
     */
    public String getUserCommand() {
        printHeader();
        out.println("What would you like to do?");

        return in.nextLine();
    }

    /**
     * Prints ALIE's goodbye message before termination.
     */
    public void showGoodbyeMessage() {
        out.println("Bye-bye! Hope to see you again soon.");
    }

    /**
     * Prints ALIE's default error message
     */
    public void showLoadingError() {
        out.println("ERROR: file not found or invalid file format.");
    }

    /**
     * Prints the result of executed commands from Obj CommandResult
     * @param command Object created post execution of command, containing message
     *                to be printed.
     */
    public void showCmdResult(CommandResult command) {
        out.println(command.feedbackToUser);
    }

    public void showError(Exception errorMsg) {
        out.println(errorMsg);
    }

    public static void printTimeWarning() {
        System.out.println("Warning: DateTime provided does not fit format, unable to parse " +
                "correctly.");
    }
}
