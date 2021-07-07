package duke.constants;

public class Messages {

    public static final String DUKE_FILE_PATH = "tasks.txt";
    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_DONE_WORD = "done";
    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_FIND_WORD = "find";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_DELETE_WORD = "delete";
    public static final String COMMAND_BYE_WORD = "bye";
    public static final String MESSAGE_LIST_TASKS = " Here are the tasks in your list:";
    public static final String MESSAGE_ADD_TASK = " Got it. I've added this task:";
    public static final String MESSAGE_LOADINGERROR = " Sorry. This file does not exist.";
    public static final String MESSAGE_MARK_DONE = " Nice! I've marked this task as done:";
    public static final String MESSAGE_MATCHING_TASKS = " Here are the matching tasks in your lists:";
    public static final String MESSAGE_GOODBYE = " Bye. Hope to see you again soon!";
    public static final String MESSAGE_REMOVED_TASKS = " Noted. I've removed this task: ";
    public static final String MESSAGE_LIST_START = "Now you have ";
    public static final String MESSAGE_LIST_END = " task in the list";
    public static final String MESSAGE_DEADLINE_DESCRIPTION_CANNOT_BE_EMPTY = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String MESSAGE_TIME_DEADLINE_CANNOT_BE_EMPTY = "☹ OOPS!!! The time for a deadline cannot be empty.";
    public static final String MESSAGE_TIME_EVENT_CANNOT_BE_EMPTY = "☹ OOPS!!! The time for an event cannot be empty.";
    public static final String MESSAGE_NO_APPROPRIATE_KEYWORD = "☹ OOPS!!! You did not fill in an appropriate keyword.";
    public static final String MESSAGE_EVENT_DESCRIPTION_CANNOT_BE_EMPTY = "☹ OOPS!!! The description of an event cannot be empty.";
    public static final String MESSAGE_DELETE_DESCRIPTION_CANNOT_BE_EMPTY = "☹ OOPS!!! The description of a delete cannot be empty.";
    public static final String MESSAGE_TODO_DESCRIPTION_CANNOT_BE_EMPTY = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String MESSAGE_NO_TASK_TO_MARK_DONE = "☹ OOPS!!! You did not provide a task to mark as done.";
    public static final String MESSAGE_NO_COMMAND_EXISTS = "☹ OOPS!!! No such command exists! Try again?";
    public static final String MESSAGE_ENTER_TASK= "Please enter a task.";
    public static final String MESSAGE_REENTER_DATE="Please enter date in the correct format dd/mm/yyyy hhmm";
    public static final String MESSAGE_REENTER_TASK_NUMBER = "Please provide a task number to delete.";
    public static final String MESSAGE_NO_TASK_NUMBER_EXISTS = "The task number you entered does not exist.";
    public static final String divider = "____________________________________________________________";

    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String lvlzero = "____________________________________________________________\n"
            + " Hello! I'm Duke \n"
            + " What can I do for you?\n"
            + "____________________________________________________________\n";


    public static void printLogoMessage(String lvlzero) {
        System.out.println(lvlzero);
    }

}
