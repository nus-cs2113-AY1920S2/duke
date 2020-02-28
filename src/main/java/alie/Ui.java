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
                    + "/        \\ . |_____ . | . |_____ ."+ System.lineSeparator();
    public static final String DIVIDER = "-------------------------------------";
    public static final String WELCOME_MSG = "Hello from" + System.lineSeparator() + logo + "What" +
            " would you like to do today?";
    public static final String GOODBYE_MSG = "Bye-bye! Hope to see you again soon.";
    public static final String FILE_FOUND_MSG = "File found. Imported data from file.";
    public static final String FILE_NOT_FOUND_ERROR = "ERROR: file not found or invalid file " +
            "format.";
    public static final String NO_TASK_FOUND_MSG = "No tasks found.";
    public static final String TASK_FOUND_MSG = "Here are the tasks in your list: "
            + System.lineSeparator();
    public static final String DATE_FORMAT_WARNING = "Warning: DateTime provided does not fit " +
            "format, unable to parse correctly.";

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
     * Print any messages with a line separator in between.
     * @param msgs Strings to be printed.
     */
    public void print(String... msgs) {
        for (String msg : msgs) {
            out.println(msg);
        }
    }

    /**
     * Getter to obtain input without leading and trailing spaces from the user
     * @return String containing user's unfiltered input.
     */
    public String getUserCommand() {
        out.print("> ");
        return in.nextLine().trim();
    }

    /**
     * Prints the result of executed commands from Obj CommandResult
     * @param command Object created post execution of command, containing message
     *                to be printed.
     */
    public void showCmdResult(CommandResult command) {
        out.println(command.feedbackToUser);
    }

    /**
     * Prints warning message for erroneous date provided.
     */
    public static void printDateWarning() {
        System.out.println(DATE_FORMAT_WARNING);
    }
}
