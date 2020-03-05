package Duke.UI;

public class Messages {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String DIVIDER = "————————————————————————————————————————-———————";

    public static final String WELCOME_MESSAGE = DIVIDER + "\nHello I'm\n" + LOGO
            + "\nWhat can I do for you?\n" + DIVIDER;
    public static final String MESSAGE_ENTER_COMMAND = "Enter command: ";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static final String MESSAGE_LOAD_ERROR = "There was a problem trying to load your file: ";
    public static final String MESSAGE_UNKNOWN_ERROR = "Something went wrong: ";

    public static final String MESSAGE_INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_DESCRIPTION = "☹ OOPS!!! The description of %s cannot be empty%s";

    public static final String MESSAGE_ADD_DELETE_SUCCESS = "Got it. I''ve %s this task: \n%s\n" +
            "Now you have %s task%s in the list.";
    public static final String TODO_ERROR_MESSAGE = ".";
    public static final String DEADLINE_EVENT_ERROR_MESSAGE = " and needs a date indicated by \"%s \".";

    public static final String MESSAGE_DONE_SUCCESS = "Nice! I've marked this task as done: \n%s";
    public static final String DONE_DELETE_ERROR_MESSAGE = " and has to be an integer in the list.";

    public static final String EMPTY_LIST_MESSAGE = "There is nothing on the list.\n";
    public static final String LIST_HEADER_MESSAGE = "Here are the tasks in your list: \n";

    public static final String MESSAGE_FIND_SUCCESS = "Here are the matching tasks in your list:\n";
    public static final String FIND_ERROR_MESSAGE = "I'm sorry but none of the tasks contain this keyword.\n";

}

