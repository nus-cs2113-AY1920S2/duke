package chatty.ui;

import chatty.task.Task;
import chatty.task.TaskList;

import java.time.LocalDate;
import java.util.Scanner;

import static chatty.util.Constants.ADDED_TASK_CONFIRMATION;
import static chatty.util.Constants.BOT_NAME;
import static chatty.util.Constants.DOT_CHARACTER;
import static chatty.util.Constants.LINE_BREAK;
import static chatty.util.Constants.SPACE_SEPARATOR;
import static chatty.util.Constants.TASK_SUMMARY;

/**
 * Handles interaction with users.
 */
public class Ui {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Reads next user command.
     *
     * @return The user command read in.
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Sends welcome message to the user.
     */
    public void sendWelcomeMessage() {
        this.sendLineBreak();
        System.out.println("Hello from " + BOT_NAME);
        System.out.println("Glad to be at your service!");
        this.sendLineBreak();
    }

    /**
     * Sends bye message to the user.
     */
    public void sendByeMessage() {
        System.out.println("Thanks for chatting with " + BOT_NAME);
        System.out.println("See you again soon!");
    }

    /**
     * Prints out all tasks in the list.
     *
     * @param taskList The list of tasks to be printed out.
     */
    public void listAllTasks(TaskList taskList) {
        if (taskList.getTotalTaskNum() == 0) {
            System.out.println("The task list is empty");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTotalTaskNum(); i++) {
            Task task = taskList.getTaskAtIdx(i);
            System.out.println((i + 1) + DOT_CHARACTER + SPACE_SEPARATOR + task);
        }
    }

    /**
     * Prints out all tasks in the list on a certain date.
     *
     * @param taskList The list of tasks to be printed out.
     * @param date     The date on which the tasks occur.
     */
    public void listTasksOnDate(TaskList taskList, LocalDate date) {
        if (taskList.getTotalTaskNum() == 0) {
            System.out.println("You have no task on date " + date);
            return;
        }

        System.out.println("Here are the tasks on " + date);
        for (int i = 0; i < taskList.getTotalTaskNum(); i++) {
            Task task = taskList.getTaskAtIdx(i);
            System.out.println((i + 1) + DOT_CHARACTER + SPACE_SEPARATOR + task);
        }
    }

    /**
     * Prints out all tasks in the list with a certain keyword.
     *
     * @param taskList The list of tasks to be printed out.
     * @param keyword  The keyword which the tasks contain.
     */
    public void listTasksWithKeyword(TaskList taskList, String keyword) {
        if (taskList.getTotalTaskNum() == 0) {
            System.out.println(String.format("There is no task with keyword '%s'", keyword));
            return;
        }

        System.out.println(String.format("Here are the tasks with keyword '%s' in your list:", keyword));
        for (int i = 0; i < taskList.getTotalTaskNum(); i++) {
            Task task = taskList.getTaskAtIdx(i);
            System.out.println((i + 1) + DOT_CHARACTER + SPACE_SEPARATOR + task.toString());
        }
    }

    /**
     * Sends default response to the user.
     */
    public void sendDefaultResponse() {
        System.out.println("Sorry, I can't help you with that yet");
        System.out.println("I'm " + BOT_NAME);
        System.out.println("How may I help you?");
    }

    /**
     * Sends message to confirm a new task added.
     *
     * @param newTask        The new task added.
     * @param totalTaskCount The total task number.
     */
    public void sendTaskAddedMessage(Task newTask, int totalTaskCount) {
        System.out.println(ADDED_TASK_CONFIRMATION);
        System.out.println(newTask);
        System.out.println(String.format(TASK_SUMMARY, totalTaskCount));
    }

    /**
     * Sends message to confirm task deletion.
     *
     * @param deletedTask The task deleted.
     */
    public void sendTaskDeletedMessage(Task deletedTask) {
        System.out.println("Successfully deleted the following task:");
        System.out.println(deletedTask);
    }

    /**
     * Sends message to confirm a task is marked as done.
     *
     * @param doneTask The task marked as done.
     */
    public void sendTaskDoneMessage(Task doneTask) {
        System.out.println("Congratulations! You've successfully marked the following task as done:");
        System.out.println(doneTask);
    }

    /**
     * Sends message to ask user follow done command format.
     */
    public void sendDoneCommandExceptionMessage() {
        System.out.println("Please follow the format 'done INDEX' for done command");
    }

    /**
     * Sends message to ask user follow todo command format.
     */
    public void sendTodoCommandExceptionMessage() {
        System.out.println("Please follow the format 'todo DESCRIPTION' for done command");
    }

    /**
     * Sends message to ask user follow deadline command format.
     */
    public void sendDeadlineCommandExceptionMessage() {
        System.out.println("Please follow the format 'deadline DESCRIPTION /by yyyy-mm-dd' for deadline command");
    }

    /**
     * Sends message to ask user follow event command format.
     */
    public void sendEventCommandExceptionMessage() {
        System.out.println("Please follow the format 'event DESCRIPTION /at yyyy-mm-dd to yyyy-mm-dd' " +
                "for event command");
    }

    /**
     * Sends message to ask user follow delete command format.
     */
    public void sendDeleteCommandExceptionMessage() {
        System.out.println("Please follow the format 'delete INDEX' for delete command");
    }

    /**
     * Sends message to ask user follow find command format.
     */
    public void sendFindCommandExceptionMessage() {
        System.out.println("Please follow the format 'find KEYWORD' for find command");
    }

    /**
     * Sends message to ask user follow date command format.
     */
    public void sendDateCommandExceptionMessage() {
        System.out.println("Please follow the format 'date yyyy-mm-dd' for date command");
    }

    /**
     * Sends message to notify the user that the input task number is out of bound.
     */
    public void sendTaskNumberOutOfBoundMessage() {
        System.out.println("The number you entered does not match any task in your list");
    }

    /**
     * Sends message to indicate that the application is reading tasks from disk.
     */
    public void sendReadingTaskMessage() {
        System.out.println("Reading tasks from disk...");
    }

    /**
     * Sends message to indicate that tasks are successfully read from disk.
     */
    public void sendReadTaskSuccessMessage() {
        System.out.println("Successfully read tasks from file");
    }

    /**
     * Sends message to indicate that the application failed to read tasks from disk.
     */
    public void sendReadTaskFailMessage() {
        System.out.println("Exception occurred while reading file...");
        System.out.println("Initializing empty tasks list");
    }

    /**
     * Sends message to indicate that the tasks are successfully saved to disk.
     */
    public void sendSaveTaskSuccessMessage() {
        System.out.println("Your tasks have been successfully saved to disk!");
    }

    /**
     * Sends message to indicate that the application has failed to save the tasks to disk.
     */
    public void sendSaveTaskFailMessage() {
        System.out.println("Oops! Exception occurred when saving data to file.");
    }

    /**
     * Prints out a line break.
     */
    public void sendLineBreak() {
        System.out.println(LINE_BREAK);
    }
}
