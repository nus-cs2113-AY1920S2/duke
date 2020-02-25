package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.util.Constants.ADD_OR_DELETE_TASK_POST_PROMPT;
import static duke.util.Constants.ADD_TASK_PROMPT;
import static duke.util.Constants.BYE_WORD;
import static duke.util.Constants.DATA_LOADED_SUCCESSFULLY_PROMPT;
import static duke.util.Constants.DATA_SAVED_SUCCESSFULLY_PROMPT;
import static duke.util.Constants.DEADLINE_COMMAND;
import static duke.util.Constants.DEADLINE_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.DEADLINE_COMMAND_SHORTCUT;
import static duke.util.Constants.DELETE_COMMAND;
import static duke.util.Constants.DELETE_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.DELETE_COMMAND_SHORTCUT;
import static duke.util.Constants.DELETE_TASKS_PROMPT;
import static duke.util.Constants.DONE_COMMAND;
import static duke.util.Constants.DONE_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.DONE_TASK_PROMPT;
import static duke.util.Constants.EVENT_COMMAND;
import static duke.util.Constants.EVENT_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.EVENT_COMMAND_SHORTCUT;
import static duke.util.Constants.EXIT_COMMAND_BYE;
import static duke.util.Constants.EXIT_COMMAND_EXIT;
import static duke.util.Constants.EXIT_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.EXIT_COMMAND_QUIT;
import static duke.util.Constants.FIND_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.FIVE_SPACES;
import static duke.util.Constants.FOUND_TASK_PROMPT;
import static duke.util.Constants.GREETING_WORD;
import static duke.util.Constants.LINE_DIVIDER;
import static duke.util.Constants.LIST_COMMAND;
import static duke.util.Constants.LIST_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.LIST_COMMAND_SHORTCUT;
import static duke.util.Constants.LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING;
import static duke.util.Constants.LIST_TASKS_PROMPT;
import static duke.util.Constants.LOAD_DATA_FROM_FILE_PROMPT;
import static duke.util.Constants.LOGO;
import static duke.util.Constants.NOT_FOUND_TASK_PROMPT;
import static duke.util.Constants.SAVE_DATA_TO_FILE_PROMPT;
import static duke.util.Constants.SEVEN_SPACES;
import static duke.util.Constants.TODO_COMMAND;
import static duke.util.Constants.TODO_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.TODO_COMMAND_SHORTCUT;

/**
 * This class is used as an interface to interact with the user. It can read the input from the user as well as print
 * message to the user.
 *
 * @author A11riseforme
 */
public class Ui {
    private Scanner in;

    /**
     * Constructor to initialize `in` as a scanner object for reading the input from the user.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Read the input from the user.
     * @return a String, which contains a line of input from the user.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Show the user that the programme is about to load the data file.
     */
    public static void showLoadDataPrompt() {
        System.out.println(FIVE_SPACES + LOAD_DATA_FROM_FILE_PROMPT);
    }

    /**
     * After successfully loading the data file, prompt user.
     */
    public static void showLoadDataSuccessfulPrompt() {
        System.out.println(FIVE_SPACES + DATA_LOADED_SUCCESSFULLY_PROMPT);
    }

    /**
     * Show the user that the programme is about to save the data into the file.
     */
    public static void showSaveDataToFilePrompt() {
        System.out.println(SAVE_DATA_TO_FILE_PROMPT);
    }

    /**
     * After successfully save the data into the data file, prompt user.
     */
    public static void showSaveDataToFileSuccessfulPrompt() {
        System.out.println(FIVE_SPACES + DATA_SAVED_SUCCESSFULLY_PROMPT);
    }

    /**
     * Show the welcome message to user when programme starts.
     */
    public void showWelcomeMessage() {
        showLine();
        System.out.println(LOGO);
        System.out.println(FIVE_SPACES + GREETING_WORD);
        showLine();
    }

    /**
     * Show the exit message when user exit the programme.
     */
    public void showGoodbyeMessage() {
        System.out.println(FIVE_SPACES + BYE_WORD);
    }

    /**
     * Show a line-divider to separate each command and output.
     */
    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    /**
     * After successfully adding one task, prompt the user.
     *
     * @param taskList the TaskList object used by the programme to store the tasks.
     * @param addedTask the Task object which refers to the newly added task.
     */
    public void showAddTaskSuccessfulPrompt(TaskList taskList, Task addedTask) {
        System.out.println(FIVE_SPACES + ADD_TASK_PROMPT);
        System.out.println(SEVEN_SPACES + addedTask);
        System.out.printf(FIVE_SPACES + ADD_OR_DELETE_TASK_POST_PROMPT, taskList.getList().size());
    }

    /**
     * After successfully mark the task as done, prompt the user.
     *
     * @param doneTask the Task object which refers to the task which the user wishes to mark as done.
     */
    public void showMarkAsDoneSuccessfulPrompt(Task doneTask) {
        System.out.println(FIVE_SPACES + DONE_TASK_PROMPT);
        System.out.println(SEVEN_SPACES + doneTask);
    }

    /**
     * Print every task in taskList
     *
     * @param taskList the TaskList object used by the programme to store the tasks.
     */
    public void showList(TaskList taskList) {

        System.out.println(FIVE_SPACES + LIST_TASKS_PROMPT);
        int taskCount = taskList.size();
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf(SEVEN_SPACES + LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING, i, taskList.get(i));
        }
    }

    public void showFind(ArrayList<Task> foundTasks) {
        if (foundTasks.size() == 0) {
            System.out.println(FIVE_SPACES + NOT_FOUND_TASK_PROMPT);
        } else {
            System.out.println(FIVE_SPACES + FOUND_TASK_PROMPT);
            int taskCount = foundTasks.size();
            for (int i = 0; i < taskCount; ++i) {
                System.out.printf(SEVEN_SPACES + LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING, i, foundTasks.get(i));
            }
        }
    }

    /**
     * After successfully deleting one task, prompt the user.
     * @param taskList the TaskList object used by the programme to store the tasks.
     * @param deletedTask the Task object which refers to the newly deleted task.
     */
    public void showDeleteTaskSuccessfulPrompt(TaskList taskList, Task deletedTask) {
        System.out.println(FIVE_SPACES + DELETE_TASKS_PROMPT);
        System.out.println(SEVEN_SPACES + deletedTask);
        System.out.printf(FIVE_SPACES + ADD_OR_DELETE_TASK_POST_PROMPT, taskList.getList().size());
    }

    /**
     * Show the usage of a specific command, if no argument or wrong argument provided, show all the commands' usage.
     * @param commandWord the command which the user want to know about how to use.
     */
    public void showHelpMessage(String commandWord) {
        switch (commandWord) {
        case EXIT_COMMAND_BYE:
        case EXIT_COMMAND_EXIT:
        case EXIT_COMMAND_QUIT:
            System.out.println(FIVE_SPACES + EXIT_COMMAND_HELP_MESSAGE);
            break;
        case LIST_COMMAND:
        case LIST_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + LIST_COMMAND_HELP_MESSAGE);
            break;
        case DONE_COMMAND:
            System.out.println(FIVE_SPACES + DONE_COMMAND_HELP_MESSAGE);
            break;
        case TODO_COMMAND:
        case TODO_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + TODO_COMMAND_HELP_MESSAGE);
            break;
        case DEADLINE_COMMAND:
        case DEADLINE_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + DEADLINE_COMMAND_HELP_MESSAGE);
            break;
        case EVENT_COMMAND:
        case EVENT_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + EVENT_COMMAND_HELP_MESSAGE);
            break;
        case DELETE_COMMAND:
        case DELETE_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + DELETE_COMMAND_HELP_MESSAGE);
            break;
        case FIND_COMMAND_HELP_MESSAGE:
            System.out.println(FIVE_SPACES + FIND_COMMAND_HELP_MESSAGE);
            break;
        default:
            showAllHelpMessage();
        }
    }

    /**
     * Show all the available commands and their respective usage.
     */
    private void showAllHelpMessage() {
        System.out.println(FIVE_SPACES + EXIT_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + LIST_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + DONE_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + TODO_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + DEADLINE_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + EVENT_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + DELETE_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + FIND_COMMAND_HELP_MESSAGE);
    }

    /**
     * Print the error message.
     *
     * @param message the error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

}
