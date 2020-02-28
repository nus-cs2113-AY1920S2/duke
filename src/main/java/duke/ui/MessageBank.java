package duke.ui;

public class MessageBank {

    public static String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String LINE_SEPARATOR = "____________________________________________________________";
    public static String WELCOME_MESSAGE = "Hello! I'm Duke\n" +
            "What can I do for you?";
    public static String GOODBYE_MESSAGE = "Goodbye. Hope to see you again soon!";
    public static String SHOW_LIST_MESSAGE = "Here are the tasks in your list:";
    public static String SHOW_LIST_EMPTY_MESSAGE = "You have no tasks in your list at the moment.";
    public static String ADD_TODO_MESSAGE = "Noted! I have added a new todo.";
    public static String ADD_DEADLINE_MESSAGE = "Noted! I have added a new deadline.";
    public static String ADD_EVENT_MESSAGE = "Noted! I have added a new event.";
    public static String DONE_TASK_MESSAGE = "Great! I have marked this task as done.";
    public static String LIST_CLEAR_MESSAGE = "Your task list has been cleared successfully!";
    public static String LIST_CLEAR_CONFIRMATION_MESSAGE = "Are you sure you want to clear all tasks in your list? Type 'Y' to confirm.";
    public static String LIST_NOT_CLEARED_MESSAGE = "You task list has not been cleared";
    public static String LIST_SIZE_MESSAGE = "You now have %d tasks in the list.";
    public static String DELETE_TASK_MESSAGE = "Got it! The following task has been successfully deleted.";
    public static String FOUND_TASK_MESSAGE = "Here are the matching tasks in your list:";
    public static String UNABLE_TO_FIND_TASK_MESSAGE = "Sorry, I am unable to find any matching tasks in your list :(";
    public static String INVALID_TODO_MESSAGE = "Oops! To add a todo, enter: ToDo {description}";
    public static String INVALID_DEADLINE_MESSAGE = "Oops! To add a Deadline with a description, enter: deadline /by {description} \n" +
            "To add a Deadline with a date(yyyy-mm-dd), enter: deadline /by {date}";
    public static String INVALID_EVENT_MESSAGE = "Oops! To add an Event, enter: event /at {location}";
    public static String INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :(.";
    public static String INVALID_FORMAT_MESSAGE = "Please follow the correct format when entering a command.";
    public static String INVALID_INDEX_MESSAGE = "The index you have entered is invalid";
    public static String ERROR_MESSAGE = "Hmm, an error has occurred...";
    public static String PROMPT_HELP_MESSAGE = "Type /help for a list of available commands";
    public static String NEW_LINE = System.lineSeparator();

}
