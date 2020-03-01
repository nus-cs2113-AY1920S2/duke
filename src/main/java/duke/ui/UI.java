package duke.ui;

import duke.tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * UI is the public class responsible for managing all user interface functions.
 */

public class UI {

    /**
     * The exit status determining whether to close the application.
     */

    private boolean exitStatus;

    /**
     * Constructs the UI object.
     */

    public UI() {
        this.exitStatus = false;
    }

    /**
     * Returns the exit status.
     * @return the exit status.
     */

    public boolean isExitStatus() {
        return this.exitStatus;
    }

    /**
     * Updates the exit status.
     * @param update the updated exit status.
     */

    public void setExitStatus(boolean update) {
        this.exitStatus = update;
    }

    /**
     * Returns the next line of user input.
     * @return the next line of user input.
     */

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the welcome message for the user.
     */

    public void displayWelcomeMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.DUKE_LOGO + MessageBank.NEW_LINE + MessageBank.WELCOME_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the goodbye message for the user.
     */

    public void displayGoodbyeMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.GOODBYE_MESSAGE);
        displayLineSeparator();
    }

    /**
     * Prints the line separator for the user.
     */

    public void displayLineSeparator() {
        System.out.println(MessageBank.LINE_SEPARATOR);
    }

    /**
     * Prints the add to-do message for the user.
     */

    public void displayAddToDoMessage(Task task, List<Task> taskList) {
        displayLineSeparator();
        System.out.println(MessageBank.ADD_TODO_MESSAGE + MessageBank.NEW_LINE + task);
        displayListSizeMessage(taskList);
        displayLineSeparator();
    }

    /**
     * Prints the add deadline message for the user.
     */

    public void displayAddDeadlineMessage(Task task, List<Task> taskList) {
        displayLineSeparator();
        System.out.println(MessageBank.ADD_DEADLINE_MESSAGE + MessageBank.NEW_LINE + task);
        displayListSizeMessage(taskList);
        displayLineSeparator();
    }

    /**
     * Prints the add event message for the user.
     */

    public void displayAddEventMessage(Task task, List<Task> taskList) {
        displayLineSeparator();
        System.out.println(MessageBank.ADD_EVENT_MESSAGE + MessageBank.NEW_LINE + task);
        displayListSizeMessage(taskList);
        displayLineSeparator();
    }

    /**
     * Prints the display list message and the tasks in the tasklist for the user.
     */

    public void displayShowListMessage(List<Task> taskList) {
        displayLineSeparator();
        if (taskList.size() == 0) {
            System.out.println(MessageBank.SHOW_LIST_EMPTY_MESSAGE);
        } else {
            System.out.println(MessageBank.SHOW_LIST_MESSAGE);
            int counter = 0;
            for (Task task : taskList) {
                counter++;
                System.out.println(counter + ". " + task);
            }
        }
        displayLineSeparator();
    }

    /**
     * Prints the done task message for the user.
     */

    public void displayDoneTaskMessage(Task task) {
        displayLineSeparator();
        System.out.println(MessageBank.DONE_TASK_MESSAGE + MessageBank.NEW_LINE + task);
        displayLineSeparator();
    }

    /**
     * Prints the display found task message and all the tasks found in the tasklist for the user.
     */

    public void displayFoundTaskMessage(List<Task> foundList) {
        displayLineSeparator();
        System.out.println(MessageBank.FOUND_TASK_MESSAGE);
        int counter = 0;
        for (Task task : foundList) {
            counter++;
            System.out.println(counter + ". " + task);
        }
        displayLineSeparator();
    }

    /**
     * Prints the unable to find task message for the user.
     */

    public void displayUnableToFindTaskMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.UNABLE_TO_FIND_TASK_MESSAGE);
        displayLineSeparator();
    }

    /**
     * Prints the list cleared message for the user.
     */

    public void displayListClearMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.LIST_CLEAR_MESSAGE);
        displayLineSeparator();
    }

    /**
     * Prints the list not cleared message for the user.
     */

    public void displayListNotClearedMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.LIST_NOT_CLEARED_MESSAGE);
        displayLineSeparator();
    }

    /**
     * Prints the clear list confirmation message for the user.
     */

    public void displayListClearConfirmationMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.LIST_CLEAR_CONFIRMATION_MESSAGE);
        displayLineSeparator();
    }

    /**
     * Prints the size of list message for the user.
     */

    public void displayListSizeMessage(List<Task> taskList) {
        System.out.format(MessageBank.LIST_SIZE_MESSAGE + MessageBank.NEW_LINE, taskList.size());
    }

    /**
     * Prints the task deleted message for the user.
     */

    public void displayDeleteTaskMessage(Task task) {
        displayLineSeparator();
        System.out.println(MessageBank.DELETE_TASK_MESSAGE + MessageBank.NEW_LINE + task);
        displayLineSeparator();
    }

    /**
     * Prints the invalid to-do message for the user.
     */

    public void displayInvalidToDoMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_TODO_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the invalid deadline message for the user.
     */

    public void displayInvalidDeadlineMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_DEADLINE_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the invalid event message for the user.
     */

    public void displayInvalidEventMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_EVENT_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the invalid format message for the user.
     */

    public void displayInvalidFormatMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_FORMAT_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the invalid command message for the user.
     */

    public void displayInvalidCommandMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_COMMAND_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the invalid index message for the user.
     */

    public void displayInvalidIndexMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_INDEX_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the error message for the user.
     */

    public void displayErrorMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.ERROR_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }

    /**
     * Prints the help menu message for the user.
     */

    public void displayHelpListMessage() {
        displayLineSeparator();
        System.out.println(HelpList.HelpListMessage());
        displayLineSeparator();
    }

    /**
     * Prints the prompt help menu message for the user.
     */

    public void displayPromptHelpMessage() {
        System.out.println(MessageBank.PROMPT_HELP_MESSAGE);
    }

}
