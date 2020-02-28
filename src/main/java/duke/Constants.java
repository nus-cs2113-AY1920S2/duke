package duke;

/**
 * contains all the common constants used throughout program
 */
public class Constants {
    public static final String STANDARD_SEPARATOR = "____________________________________________________________";
    public static final String SHANNON = "Shannon";
    public static final String ioErrorMessage = " OOPS!! There is an error in input or output";
    public static final String unknownCommandMessage = " OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String errMissingParam = "OOPS!!! The description is incomplete.";
    public static final String helpManual = "Hi! This is a list of all the commands that Shannon's Duke offers"
            + System.lineSeparator() + STANDARD_SEPARATOR
            + System.lineSeparator() + "todo <DESCRIPTION>  --  Adds a todo Task into the list"
            + System.lineSeparator() + "deadline <DESCRIPTION> /by <DEADLINE>  --  Adds a deadline Task into the list"
            + System.lineSeparator() + "event <DESCRIPTION> /at <DEADLINE>  --  Adds an event Task into the list"
            + System.lineSeparator() + STANDARD_SEPARATOR
            + System.lineSeparator() + "remove <INDEX>  --  removes task at index from the list"
            + System.lineSeparator() + "list  --  prints out the contents of the list"
            + System.lineSeparator() + "done <INDEX>  --  Marks task at index as done"
            + System.lineSeparator() + "find <KEYWORD>  --  finds and prints tasks containing keywords"
            + System.lineSeparator() + "bye <KEYWORD>  --  exits from program";
}
