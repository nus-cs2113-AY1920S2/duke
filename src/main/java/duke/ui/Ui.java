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
import static duke.util.Constants.FIVE_SPACES;
import static duke.util.Constants.GREETING_WORD;
import static duke.util.Constants.LINE_DIVIDER;
import static duke.util.Constants.LIST_COMMAND;
import static duke.util.Constants.LIST_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.LIST_COMMAND_SHORTCUT;
import static duke.util.Constants.LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING;
import static duke.util.Constants.LIST_TASKS_PROMPT;
import static duke.util.Constants.LOAD_DATA_FROM_FILE_PROMPT;
import static duke.util.Constants.LOGO;
import static duke.util.Constants.SAVE_DATA_TO_FILE_PROMPT;
import static duke.util.Constants.SEVEN_SPACES;
import static duke.util.Constants.TODO_COMMAND;
import static duke.util.Constants.TODO_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.TODO_COMMAND_SHORTCUT;

public class Ui {
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public static void showLoadDataSuccessfulPrompt() {
        System.out.println(FIVE_SPACES + DATA_LOADED_SUCCESSFULLY_PROMPT);
    }

    public static void showLoadDataPrompt() {
        System.out.println(FIVE_SPACES + LOAD_DATA_FROM_FILE_PROMPT);
    }

    public static void showSaveDataToFileSuccessfulPrompt() {
        System.out.println(FIVE_SPACES + DATA_SAVED_SUCCESSFULLY_PROMPT);
    }

    public void showWelcomeMessage() {
        showLine();
        System.out.println(LOGO);
        System.out.println(FIVE_SPACES + GREETING_WORD);
        showLine();
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showGoodbyeMessage() {
        System.out.println(FIVE_SPACES + BYE_WORD);
    }

    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    public void showAddTaskSuccessfulPrompt(TaskList taskList, Task addedTask) {
        System.out.println(FIVE_SPACES + ADD_TASK_PROMPT);
        System.out.println(SEVEN_SPACES + addedTask);
        System.out.printf(FIVE_SPACES + ADD_OR_DELETE_TASK_POST_PROMPT, taskList.getList().size());
    }

    public void showMarkAsDoneSuccessfulPrompt(Task doneTask) {
        System.out.println(FIVE_SPACES + DONE_TASK_PROMPT);
        System.out.println(SEVEN_SPACES + doneTask);
    }

    public void showList(TaskList taskList) {
        System.out.println(FIVE_SPACES + LIST_TASKS_PROMPT);
        ArrayList<Task> list = taskList.getList();
        int taskCount = list.size();
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf(SEVEN_SPACES + LIST_SINGLE_TASK_MESSAGE_FORMAT_STRING, i, list.get(i));
        }
    }

    public void showDeleteTaskSuccessfulPrompt(TaskList taskList, Task deletedTask) {
        System.out.println(FIVE_SPACES + DELETE_TASKS_PROMPT);
        System.out.println(SEVEN_SPACES + deletedTask);
        System.out.printf(FIVE_SPACES + ADD_OR_DELETE_TASK_POST_PROMPT, taskList.getList().size());
    }

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
        default:
            showAllHelpMessage();
        }
    }

    private void showAllHelpMessage() {
        System.out.println(FIVE_SPACES + EXIT_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + LIST_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + DONE_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + TODO_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + DEADLINE_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + EVENT_COMMAND_HELP_MESSAGE);
        System.out.println(FIVE_SPACES + DELETE_COMMAND_HELP_MESSAGE);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public static void showSaveDataToFilePrompt() {
        System.out.println(SAVE_DATA_TO_FILE_PROMPT);
    }
}
