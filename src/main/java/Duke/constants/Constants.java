package duke.constants;

/**
 * A class file containing the magic literals and constant strings
 */
public class Constants {
    public static final String VERSION = "TASK MANAGER - Version 1.1";

    public static final String DIVIDER = "=============================================================================================================";

    public static final String MESSAGE_WELCOME = "Welcome to BAPE, your Task Manager!\n"
            + "Here are the functions available.";

    public static final String LOGO = " _____     _      ______ ______\n"
            + "|  _  \\   / \\    |  __  |  ____|\n"
            + "| |_| |  / _ \\   | |__| | |___\n"
            + "|  _  | / /_\\ \\  |  ____|  ___|\n"
            + "| |_| |/ _____ \\ | |    | |____\n"
            + "|_____/_/     \\_\\|_|    |______|\n";

    public static final String FUNCTION_LIST = " ___________________________________________________________________________________________________________\n"
            + "|  Functions:  |                 Descriptions:                      |               Example:                |\n"
            + "|______________|____________________________________________________|_______________________________________|\n"
            + "|   todo       |                Create a To-do task                 | (eg. todo borrow books)               |\n"
            + "|   deadline   |                Create a task with a deadline       | (eg. deadline bathe /by 9PM)          |\n"
            + "|   event      |                Create an event task                | (eg. event Meeting /at Library, 12PM) |\n"
            + "|   list       |                List all the task in your planner   |                                       |\n"
            + "|   done       |                Mark a task as completed            | (eg. done 2)                          |\n"
            + "|   find       |                Find task with keyword              | (eg. find book)                       |\n"
            + "|   reset      |                reset program and Tasklist.txt      |                                       |\n"
            + "|   bye        |                Exit Planner                        |                                       |\n"
            + "|   help       |                Show this table                     |                                       |\n"
            + "|______________|____________________________________________________|_______________________________________|\n"
            + "LEGEND:   [O] ---- DONE | [X] ---- NOT DONE \n"
            + "Please key in your command: ";

    public static final String LINE_DIVIDER = "____________________________________________________________";

    public static final String INDENT = "    ";

    public static final String EMPTY_LINE = "\n";

    public static final String TASK_DELETED_MESSAGE = "____________________________________________________________\n"
            + "\n    Noted. I've removed this task:";

    public static final String TASKNUMBER_LEFT_MESSAGE = "    Total number of tasks in your list: ";

    public static final String TASK_DONE_MESSAGE = "    Great job! I've marked this task as done in your planner: ";

    public static final String TASK_LEFT_MESSAGE = "    Here are your task(s) currently in your planner:";

    public static final String EMPTY_TASKS_MESSAGE = "    Your planner is empty currently! Try adding some tasks first!";

    public static final String TASK_ADDED_MESSAGE = "    New task added:";

    public static final String MATCHING_TASK_MESSAGE = "____________________________________________________________\n"
            + "\n    Here are the matching tasks in your list:\n";

    public static final String NO_MATCH_MESSAGE = "    Sorry! There are no task with descriptions matching your keyword! Please try again!\n\n";

    public static final String BYE_MESSAGE = "    Bye! Hope to see you again soon!\n";

    public static final String INVALID_COMMAND_MESSAGE = "    Please key in valid function!\n";

    public static final String DESCRIPTIONS_MISSING = "____________________________________________________________\n"
            + "\n    Task description is missing! Please try again.\n"
            + "\n____________________________________________________________";

    public static final String TASK_NUM_COMPLETED = "____________________________________________________________\n"
            + "\n    Please key in the task number that you have completed.\n"
            + "\n____________________________________________________________\n";

    public static final String TASK_NUM_DELETED = "____________________________________________________________\n"
            + "\n    Please key in the task number that you wish to delete.\n"
            + "\n____________________________________________________________\n";

    public static final String FIND_DESCRIPTIONS = "____________________________________________________________\n"
            + "\n    Please key in the description of task you wish to find.\n"
            + "\n____________________________________________________________\n";

    public static final String DEADLINE_DESCRIPTIONS_EMPTY = "    The deadline description cannot be empty! Please try " +
            "again or type 'help' to check input format.\n";

    public static final String DATETIME_EMPTY = "____________________________________________________________\n"
            + "\n    Date/time not found! Please try again or type 'help' to check input format.\n"
            + "\n____________________________________________________________\n";

    public static final String EVENT_DESCRIPTIONS_EMPTY = "    The event description cannot be empty! Please try again " +
            "or type 'help' to check input format.\n";

    public static final String LOCATION_EMPTY = "____________________________________________________________\n"
            + "\n    Location not found! Please try again or type 'help' to check input format.\n"
            + "\n____________________________________________________________\n";

    public static final String INDEX_OUT_OF_BOUND = "____________________________________________________________\n"
            + "\n    No task associated with the task number. Please check again.\n"
            + "\n____________________________________________________________\n";

    public static final String NOT_INTEGER = "____________________________________________________________\n"
            + "\n    Please key in an integer.\n"
            + "\n____________________________________________________________\n";
}
