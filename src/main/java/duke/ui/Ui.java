package duke.ui;

import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;

import java.util.Scanner;

public class Ui {
    private static Scanner scannerObject;
    private static final String LINE_SEPARATOR = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String GOODBYE_MESSAGE = "Leaving so soon? :(";
    private static final String NEWLINE = System.lineSeparator();
    private static final String GREETING = "Hello! This is Quinn's chat bot" + NEWLINE +
            "Currently supported commands: todo, deadline, event, list, done, delete" + NEWLINE +
            "Example usage:" + NEWLINE +
            ToDoCommand.EXAMPLE_USAGE + NEWLINE +
            DeadlineCommand.EXAMPLE_USAGE+ NEWLINE +
            EventCommand.EXAMPLE_USAGE + NEWLINE +
            ListCommand.EXAMPLE_USAGE + NEWLINE +
            DoneCommand.EXAMPLE_USAGE + NEWLINE +
            DeleteCommand.EXAMPLE_USAGE + NEWLINE +
            "Type \"" + duke.Main.END_STRING + "\" to exit";


    public static void initialize() {
        scannerObject = new Scanner(System.in);
    }

    public static void greet() {
        printPretty(GREETING);
    }

    public static void sayGoodbye() {
        printPretty(GOODBYE_MESSAGE);
    }

    public static void printPretty(String message) {
        System.out.println();
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
        System.out.println();
    }

    public static String getNextLine() {
        return scannerObject.nextLine();
    }
}
