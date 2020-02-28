package alie;

import alie.commands.CommandResult;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    public static final String logo =
                      "    /\\       |        |   |‾‾‾‾‾" + System.lineSeparator()
                    + "   /  \\      |        |   |"      + System.lineSeparator()
                    + "  /____\\     |        |   |----"  + System.lineSeparator()
                    + " /      \\    |        |   |"      + System.lineSeparator()
                    + "/        \\ . |_____ . | . |_____ .";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        out.println("Hello from\n" + logo);
    }

    public static void printHeader() {
        System.out.print("ALIE> ");
    }

    public String getUserCommand() {
        printHeader();
        out.println("What would you like to do?");

        return in.nextLine();
    }

    public void showGoodbyeMessage() {
        out.println("Bye-bye! Hope to see you again soon.");
    }

    public void showLoadingError() {
        out.println("ERROR: file not found or invalid file format.");
    }

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
