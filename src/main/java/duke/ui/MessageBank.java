package duke.ui;

/**
 * MessageBank is the public class responsible for storing all user interface messages.
 */

public class MessageBank {

    /**
     * Displays the Duke Logo.
     */

    public static String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Displays a line separator.
     */

    public static String LINE_SEPARATOR = "____________________________________________________________";

    /**
     * Displays the welcome message.
     */

    public static String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";

    /**
     * Displays the goodbye message.
     */

    public static String GOODBYE_MESSAGE = "Goodbye. Hope to see you again soon!";

    /**
     * Displays the show list message.
     */

    public static String SHOW_LIST_MESSAGE = "Here are the tasks in your list:";

    /**
     * Displays the list is empty message.
     */

    public static String SHOW_LIST_EMPTY_MESSAGE = "You have no tasks in your list at the moment.";

    /**
     * Displays the successfully added to-do message.
     */

    public static String ADD_TODO_MESSAGE = "Noted! I have added a new todo.";

    /**
     * Displays the successfully added deadline message.
     */

    public static String ADD_DEADLINE_MESSAGE = "Noted! I have added a new deadline.";

    /**
     * Displays the successfully added event message.
     */

    public static String ADD_EVENT_MESSAGE = "Noted! I have added a new event.";

    /**
     * Displays the successfully marked task as done message.
     */

    public static String DONE_TASK_MESSAGE = "Great! I have marked this task as done.";

    /**
     * Displays the successfully cleared tasklist message.
     */

    public static String LIST_CLEAR_MESSAGE = "Your task list has been cleared successfully!";

    /**
     * Displays the clear tasklist confirmation message.
     */

    public static String LIST_CLEAR_CONFIRMATION_MESSAGE = "Are you sure you want to clear all tasks in your list? Type 'Y' to confirm.";

    /**
     * Displays the did not clear tasklist message.
     */

    public static String LIST_NOT_CLEARED_MESSAGE = "You task list has not been cleared.";

    /**
     * Displays the size of tasklist message.
     */

    public static String LIST_SIZE_MESSAGE = "You now have %d tasks in the list.";

    /**
     * Displays the successfully deleted task message.
     */

    public static String DELETE_TASK_MESSAGE = "Got it! The following task has been successfully deleted:";

    /**
     * Displays the successfully found task message.
     */

    public static String FOUND_TASK_MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Displays the unable to find task message.
     */

    public static String UNABLE_TO_FIND_TASK_MESSAGE = "Sorry, I am unable to find any matching tasks in your list :(";

    /**
     * Displays the unable to add to-do message.
     */

    public static String INVALID_TODO_MESSAGE = "Oops! To add a ToDo, enter: todo description";

    /**
     * Displays the unable to add deadline message.
     */

    public static String INVALID_DEADLINE_MESSAGE = "To add a Deadline, date in yyyy-mm-dd or otherwise, enter: deadline /by date";

    /**
     * Displays the unable to add event message.
     */

    public static String INVALID_EVENT_MESSAGE = "Oops! To add an Event, enter: event /at location";

    /**
     * Displays the invalid command message.
     */

    public static String INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :(.";

    /**
     * Displays the invalid command format message.
     */

    public static String INVALID_FORMAT_MESSAGE = "Please follow the correct format when entering a command.";

    /**
     * Displays the invalid index entered message.
     */

    public static String INVALID_INDEX_MESSAGE = "The index you have entered is invalid.";

    /**
     * Displays the error message.
     */

    public static String ERROR_MESSAGE = "Hmm, an error has occurred...";

    /**
     * Displays the prompt help menu message.
     */

    public static String PROMPT_HELP_MESSAGE = "Type /help for a list of supported commands.";

    /**
     * Displays a new line.
     */

    public static String NEW_LINE = System.lineSeparator();

}
