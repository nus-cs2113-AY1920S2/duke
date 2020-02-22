package duke.util;

public class Constants {
    public static final String LOGO = "          __ __      _           __                          \n"
                                    + "     /\\  /_ /_ |    (_)         / _|                         \n"
                                    + "    /  \\  | || |_ __ _ ___  ___| |_ ___  _ __ _ __ ___   ___ \n"
                                    + "   / /\\ \\ | || | '__| / __|/ _ \\  _/ _ \\| '__| '_ ` _ \\ / _ \\\n"
                                    + "  / ____ \\| || | |  | \\__ \\  __/ || (_) | |  | | | | | |  __/\n"
                                    + " /_/    \\_\\_||_|_|  |_|___/\\___|_| \\___/|_|  |_| |_| |_|\\___|\n";
    public static final String LINE_DIVIDER = "    ____________________________________________________________";
    public static final String FIVE_SPACES = "     ";
    public static final String SEVEN_SPACES = "       ";
    public static final String GREETING_WORD = "Hello! I'm A11riseforme\n     What can I do for you?";
    public static final String BYE_WORD = "Bye. Hope to see you again soon!";
    public static final String EXIT_COMMAND_BYE = "bye";
    public static final String EXIT_COMMAND_QUIT = "quit";
    public static final String EXIT_COMMAND_EXIT = "exit";
    public static final String LIST_COMMAND = "list";
    public static final String LIST_COMMAND_SHORTCUT = "ls";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String TODO_COMMAND_SHORTCUT = "td";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String DEADLINE_COMMAND_SHORTCUT = "ddl";
    public static final String EVENT_COMMAND = "event";
    public static final String EVENT_COMMAND_SHORTCUT = "evt";
    public static final String DELETE_COMMAND = "delete";
    public static final String DELETE_COMMAND_SHORTCUT = "del";
    public static final String HELP_COMMAND = "help";
    public static final String DEADLINE_TIME_DELIMITER = " -by ";
    public static final String EVENT_TIME_DELIMITER = " -at ";
    public static final String UNKNOWN_COMMAND_RESPONSE = " OOPS!!! I don't know what that means\n" + FIVE_SPACES +
            "You may enter `help` to check available commands!";
    public static final String LIST_TASKS_PROMPT = "Here are the tasks in your list:";
    public static final String DONE_TASK_PROMPT = "Nice! I've marked this duke.task as done:";
    public static final String ADD_TASK_PROMPT = "Got it. I've added this duke.task:";
    public static final String ADD_OR_DELETE_TASK_POST_PROMPT = "Now you have %d tasks in the list.\n";
    public static final String DELETE_TASKS_PROMPT = "Noted. I've removed this task:";
    public static final String LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING = "%d.%s\n";
    public static final String TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE = " OOPS!!! The task id is not provided " +
            "or invalid!";
    public static final String DEADLINE_FORMAT_ERROR_MESSAGE = " OOPS!!! The deadline format is not correct!";
    public static final String EVENT_FORMAT_ERROR_MESSAGE = " OOPS!!! The event format is not correct!";
    public static final String TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE = " OOPS!!! The task description cannot be empty!";
    public static final String DONE_COMMAND_HELP_MESSAGE = "key in `done <task_id>` to mark the task of <task_id> as " +
            "done.";
    public static final String LIST_COMMAND_HELP_MESSAGE = "key in `list` or `ls` to list all the tasks.";
    public static final String TODO_COMMAND_HELP_MESSAGE = "key in `todo/td <task_description>` to add a todo task.";
    public static final String DEADLINE_COMMAND_HELP_MESSAGE = "key in `deadline/ddl <task_description> -by " +
            "<deadline>` to add a deadline task.";
    public static final String EVENT_COMMAND_HELP_MESSAGE = "key in `event/evt <task_description> -at <event_date>` " +
            "to add an event task.";
    public static final String DELETE_COMMAND_HELP_MESSAGE = "key in `delete/del <task_id>` to delete the task of " +
            "<task_id>.";
    public static final String EXIT_COMMAND_HELP_MESSAGE = "key in `exit/bye/quit` to exit the programme.";
    public static final String YES_ICON = "[v]";
    public static final String NO_ICON = "[x]";
    public static final String CRYING_FACE = ":-(";
    public static final String DEADLINE_ICON = "[D]";
    public static final String EVENT_ICON = "[E]";
    public static final String TODO_ICON = "[T]";
    public static final String DATA_FILE_PATH = "tasks.json";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = " OOPS!!! The DATA_FILE_PATH does not exist!";
    public static final String FILE_OPERATION_IO_ERROR_MESSAGE = " OOPS!!! Something went wrong here!";
    public static final String LOAD_DATA_FROM_FILE_PROMPT_FORMAT_STRING = "Loading data from %s, please wait...\n";
    public static final String DATA_LOADED_SUCCESSFULLY_PROMPT = "Data loaded successfully!";
    public static final String SAVE_DATA_TO_FILE_PROMPT_FORMAT_STRING = "Saving data to %s, please wait...\n";
    public static final String DATA_SAVED_SUCCESSFULLY_PROMPT = "Data saved successfully!";
}
