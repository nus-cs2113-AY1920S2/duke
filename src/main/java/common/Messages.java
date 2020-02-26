package common;
import data.task.*;
import ui.TextUi;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String LOGO = "                     __ ____  ______  ____\n" +
            "                    / //_/ / / / __ \\/  _/\n" +
            "                   / ,< / / / / /_/ // /  \n" +
            "                  / /| / /_/ / _, _// /   \n" +
            "                 /_/ |_\\____/_/ |_/___/   \n" +
            "                         \n";
    public static final int INDEX_OFF_SET = -1;
    public static final char EVENT_TYPE = 'E';
    public static final char DEADLINE_TYPE = 'D';
    public static final char DONE = 'D';
    public static final char notDONE = 'N';
    public static final String DIVIDER = "----------------------------------------------------";
    public static final String MESSAGE_SPLITTER = "=============================" +
            "====================================================";
    public static final String MESSAGE_WELCOME_1 = "Welcome to KURI Task Management System?";
    public static final String MESSAGE_WELCOME_2 = "What can I do for you?";
    public static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASK_NOT_IN_TASKLIST = "The Task could not be found in address book";
    public static final String MESSAGE_TODO_LIST = "%d. [Id:%d][%c][%c] %s";
    public static final String MESSAGE_DEADLINE_LIST = "%d. [Id:%d][%c][%c] %s (%s)";
    public static final String MESSAGE_EVENT_LIST = "%d. [Id:%d][%c][%c] %s (%s)";
    public static final String MESSAGE_FILE_OPERATION_IO_ERROR = "Error writing to file: %s";
    public static final String MESSAGE_DUPLICATE_TASK_ALERT = "This task is similar to task Index %d\nDo you want to add a duplicate task? Press Y to add and others to not add: ";
    public static final String MESSAGE_DUPLICATE_TASK_NOT_ADDED = "The duplicate task is not added!";
    public static final String MESSAGE_ASK_TO_CHOOSE_UI = "Please enter '1' for GUI and '2' for CLI: ";
    public static final String MESSAGE_THANKS_FOR_GUI = "Thanks for choosing GUI";
    public static final String MESSAGE_THANKS_FOR_CLI = "Thanks for choosing CLI";
    public static final String MESSAGE_INVALID_USER_CHOICE = "Invalid Choice!";
    public static final String MESSAGE_ALERT = "[ALERT!]";
    public static final String MESSAGE_SHOW_TASK_NUMBER = "There are total %d task(s) in the list";
    public static StringBuilder taskListMessage;
    public static final String respondFormat = "===-             %-70s-===%n";
    public static final String MESSAGE_LIST_RESPOND_Format = "%-60s";
    public static final String MESSAGE_RS = "-===";
    public static final String MESSAGE_LS = "===-             ";

    /**
     * Print all tasks in the task list
     */
    public static String printAllTasks(TaskList tasklist){
        String taskMessage = "";
        taskListMessage = new StringBuilder();
        getTaskListMessage(tasklist, taskMessage);
        return taskListMessage.toString();
    }

    /**
     * get tasklist message
     * @param tasklist
     * @param taskMessage
     */
    private static void getTaskListMessage(TaskList tasklist, String taskMessage) {
        for (int index = 1; index <= tasklist.getInternalList().size() ; index++) {
            Task task = tasklist.getInternalList().get(index+ INDEX_OFF_SET);
            printTaskMessage(index, task);
        }
        TextUi.printMessage(TextUi.SYSTEM_COLOR_RESPONSE,
                String.format(MESSAGE_SHOW_TASK_NUMBER,
                tasklist.getInternalList().size()) );
    }

    /**
     * get task message
     * @param index
     * @param task
     * @return
     */
    private static void printTaskMessage(int index, Task task) {
        if (task instanceof TodoTask) {
            TextUi.printTodoTask((TodoTask) task, index);
        } else if (task instanceof DeadlineTask) {
            TextUi.printDeadlineTask((DeadlineTask) task, index);
        } else if( task instanceof EventTask) {
            TextUi.printEventTask((EventTask) task, index);
        }
    }
}
