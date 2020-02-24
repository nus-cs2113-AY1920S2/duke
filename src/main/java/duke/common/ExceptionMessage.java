package duke.common;

public class ExceptionMessage {
    
    private static final String LS = System.lineSeparator();
    public static final String COMMAND_INVALID_MESSAGE = "Invalid Command, please try another command" + LS +
            "type \"help\" in the command line console for instructions";
    public static final String COMMAND_INVALID_INDEX_MESSAGE = "Invalid Index";
    public static final String MISSING_DATE_MESSAGE_EXCEPTION = "OOPS!!! The date of a %s cannot be empty.";
    public static final String MISSING_DESCRIPTION_MESSAGE_EXCEPTION =
            "OOPS!!! The description of a %s cannot be empty.";
    
}
