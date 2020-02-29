package duke.ui;

import duke.tasks.Task;

import java.util.List;
import java.util.Scanner;

public class UI {

    private boolean exitStatus;

    public UI() {
        this.exitStatus = false;
    }

    public boolean isExitStatus() {
        return this.exitStatus;
    }

    public void setExitStatus(boolean update) {
        this.exitStatus = update;
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void displayWelcomeMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.DUKE_LOGO + MessageBank.NEW_LINE + MessageBank.WELCOME_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayGoodbyeMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.GOODBYE_MESSAGE);
        displayLineSeparator();
    }
    public void displayLineSeparator() {
        System.out.println(MessageBank.LINE_SEPARATOR);
    }
    public void displayAddToDoMessage(Task task, List<Task> taskList) {
        displayLineSeparator();
        System.out.println(MessageBank.ADD_TODO_MESSAGE + MessageBank.NEW_LINE + task);
        displayListSizeMessage(taskList);
        displayLineSeparator();
    }
    public void displayAddDeadlineMessage(Task task, List<Task> taskList) {
        displayLineSeparator();
        System.out.println(MessageBank.ADD_DEADLINE_MESSAGE + MessageBank.NEW_LINE + task);
        displayListSizeMessage(taskList);
        displayLineSeparator();
    }
    public void displayAddEventMessage(Task task, List<Task> taskList) {
        displayLineSeparator();
        System.out.println(MessageBank.ADD_EVENT_MESSAGE + MessageBank.NEW_LINE + task);
        displayListSizeMessage(taskList);
        displayLineSeparator();
    }
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
    public void displayDoneTaskMessage(Task task) {
        displayLineSeparator();
        System.out.println(MessageBank.DONE_TASK_MESSAGE + MessageBank.NEW_LINE + task);
        displayLineSeparator();
    }
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
    public void displayUnableToFindTaskMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.UNABLE_TO_FIND_TASK_MESSAGE);
        displayLineSeparator();
    }
    public void displayListClearMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.LIST_CLEAR_MESSAGE);
        displayLineSeparator();
    }
    public void displayListNotClearedMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.LIST_NOT_CLEARED_MESSAGE);
        displayLineSeparator();
    }
    public void displayListClearConfirmationMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.LIST_CLEAR_CONFIRMATION_MESSAGE);
        displayLineSeparator();
    }
    public void displayListSizeMessage(List<Task> taskList) {
        System.out.format(MessageBank.LIST_SIZE_MESSAGE + MessageBank.NEW_LINE, taskList.size());
    }
    public void displayDeleteTaskMessage(Task task) {
        displayLineSeparator();
        System.out.println(MessageBank.DELETE_TASK_MESSAGE + MessageBank.NEW_LINE + task);
        displayLineSeparator();
    }
    public void displayInvalidToDoMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_TODO_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayInvalidDeadlineMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_DEADLINE_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayInvalidEventMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_EVENT_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayInvalidFormatMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_FORMAT_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayInvalidCommandMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_COMMAND_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayInvalidIndexMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.INVALID_INDEX_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayErrorMessage() {
        displayLineSeparator();
        System.out.println(MessageBank.ERROR_MESSAGE);
        displayPromptHelpMessage();
        displayLineSeparator();
    }
    public void displayHelpListMessage() {
        displayLineSeparator();
        System.out.println(HelpList.HelpListMessage());
        displayLineSeparator();
    }
    public void displayPromptHelpMessage() {
        System.out.println(MessageBank.PROMPT_HELP_MESSAGE);
    }

}
