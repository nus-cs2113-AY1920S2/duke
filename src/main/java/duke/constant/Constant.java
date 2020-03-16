package duke.constant;

/**
 * The Constant class is the class that keeps a record of all the constants used across all Duke package.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Constant {
    // Command Keywords for all the command
    public static final String TODO_COMMAND_KEYWORD = "todo";
    public static final String DEADLINE_COMMAND_KEYWORD = "deadline";
    public static final String EVENT_COMMAND_KEYWORD = "event";
    public static final String LIST_COMMAND_KEYWORD = "list";
    public static final String DONE_COMMAND_KEYWORD = "done";
    public static final String DELETE_COMMAND_KEYWORD = "delete";
    public static final String FIND_COMMAND_KEYWORD = "find";
    public static final String HELP_COMMAND_KEYWORD = "help";
    public static final String BYE_COMMAND_KEYWORD = "bye";

    // Command delimiter for deadline and event
    public static final String DEADLINE_COMMAND_DELIMITER = " /by ";
    public static final String EVENT_COMMAND_DELIMITER = " /at ";

    // File path and name for storing and loading of data
    public static final String FILE_PATH = "data/duke.txt";
    public static final String FILE_NAME = "data";
}
