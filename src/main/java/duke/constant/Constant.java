package duke.constant;

public final class Constant {
    public static final int TASK_LIMIT = 100;
    public static final String TAB_SPACE = "\t";
    public static final String DIVIDER = TAB_SPACE + "____________________________________________________________\n";
    public static final String BYE = "bye";
    public static final String BYE_MESSAGE = TAB_SPACE + "Bye. Hope to see you again soon!";
    public static final String HELLO_MESSAGE = TAB_SPACE + "Hello! I'm Duke\n";
    public static final String HELP_MESSAGE = TAB_SPACE + "It seems like you are needing some help.\n";
    public static final String DONE = "done";
    public static final String LIST = "list";
    public static final String TASK_MARKING_MESSAGE = TAB_SPACE + "Nice! I've marked this task as done:";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TASK_LISTING = TAB_SPACE + "Here are the tasks in your list:";
    public static final String ADDED_TASK_MESSAGE = TAB_SPACE + "Got it. I've added this task:";
    public static final int TODO_WORD_LENGTH = 4;
    public static final int DEADLINE_WORD_LENGTH = 8;
    public static final int EVENT_WORD_LENGTH = 5;
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String EMPTY_INPUT_MESSAGE = TAB_SPACE + "☹ OOPS!!! Input cannot be empty";
    public static final String DUMMY_INPUT_MESSAGE = TAB_SPACE + "☹ OOPS!!! I'm sorry, but I don't know what "
                                                    + "that means :-(";
    public static final String LIMIT_REACHED_MESSAGE = TAB_SPACE + "☹ OOPS!!! duke.task.Task limit has reached";
    public static final String WRONG_NUMBER_FORMAT_MESSAGE = TAB_SPACE + "☹ OOPS!!! Wrong number format!";
    public static final String OUT_OF_BOUND_MESSAGE = TAB_SPACE + "☹ OOPS!!! The task's index "
                                                    + "is out of bound or does not exist";
    public static final String TASK_REMOVED_MESSAGE = TAB_SPACE + "Noted. I've removed this task:";
    public static final String DELETE = "delete";
    public static final String VERTICAL_BAR = "|";
    public static final String TODO_ABBREVIATION = "T";
    public static final String DEADLINE_ABBREVIATION = "D";
    public static final String EVENT_ABBREVIATION = "E";
    public static final String PATH = "data/duke.txt";
    public static final String ONE = "1";
    public static final String FIND = "find";
    public static final String VERTICAL_BAR_REGREX = "\\|";
    public static final String DEADLINE_TYPE = "[D]";
    public static final String TODO_TYPE = "[T]";
    public static final String EVENT_TYPE = "[E]";
    public static final String DATE_FORMAT_MESSAGE = TAB_SPACE + "☹ OOPS!!! Please input the correct date format"
                                                    + "in form of yyyy-mm-dd";
    public static final String THE_MATCHING_TASKS_IN_YOUR_LIST = "\tHere are the matching tasks in your list:";
    public static final String DIRECTORY_PATH = "data";
    public static final String SLASH_SEPARATOR = "/";
    public static final String ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but there is an error :-(";
    public static final String DIRECTORY_DOES_NOT_EXIST = "\tThe directory does not exist,"
                                                        + "currently creating a new one\n";

    private Constant() {

    }
}
