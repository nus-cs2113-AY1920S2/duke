package chatty.ui;

import chatty.task.Task;
import chatty.task.TaskList;

import static chatty.util.Constants.ADDED_TASK_CONFIRMATION;
import static chatty.util.Constants.BOT_NAME;
import static chatty.util.Constants.DOT_CHARACTER;
import static chatty.util.Constants.LINE_BREAK;
import static chatty.util.Constants.SPACE_SEPARATOR;
import static chatty.util.Constants.TASK_SUMMARY;

public class Ui {

    public void sendWelcomeMessage() {
        System.out.println("Hello from " + BOT_NAME);
        System.out.println("Glad to be at your service!");
    }

    public void sendByeMessage() {
        System.out.println("Thanks for chatting with " + BOT_NAME);
        System.out.println("See you again soon!");
    }

    public void listAllTasks(TaskList taskList) {
        if (taskList.getTotalTaskNum() == 0) {
            System.out.println("The task list is empty");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTotalTaskNum(); i++) {
            Task task = taskList.getTaskAtIdx(i);
            System.out.println((i + 1) + DOT_CHARACTER + SPACE_SEPARATOR + task.toString());
        }
    }

    public void sendDefaultResponse() {
        System.out.println("Sorry, I can't help you with that yet");
        System.out.println("I'm " + BOT_NAME);
        System.out.println("How may I help you?");
    }

    public void sendTaskAddedMessage(Task newTask, int totalTaskCount) {
        System.out.println(ADDED_TASK_CONFIRMATION);
        System.out.println(newTask);
        System.out.println(String.format(TASK_SUMMARY, totalTaskCount));
    }

    public void sendTaskDeletedMessage(Task deletedTask) {
        System.out.println("Successfully deleted the following task:");
        System.out.println(deletedTask);
    }

    public void sendTaskDoneMessage(Task doneTask) {
        System.out.println("Congratulations! You've successfully marked the following task as done:");
        System.out.println(doneTask);
    }

    public void askForMoreDetails() {
        System.out.println("Please let me know more details so that I can help you");
    }

    public void sendTaskNumberOutOfBoundMessage() {
        System.out.println("The number you entered does not match any task in your list");
    }

    public void sendWrongTaskNumberFormatMessage() {
        System.out.println("Please enter a valid task number");
    }

    public void sendWrongDateTimeFormatMessage() {
        System.out.println("Please use correct date time format: yyyy-mm-dd");
    }

    public void sendReadingTaskMessage() {
        System.out.println("Reading tasks from disk...");
    }

    public void sendReadTaskSuccessMessage() {
        System.out.println("Successfully read tasks from file");
    }

    public void sendReadTaskFailMessage() {
        System.out.println("Exception occurred while reading file...");
        System.out.println("Initializing empty tasks list");
    }

    public void sendSaveTaskSuccessMessage() {
        System.out.println("Your tasks have been successfully saved to disk!");
    }

    public void sendSaveTaskFailMessage() {
        System.out.println("Oops! Exception occurred when saving data to file.");
    }

    public void sendLineBreak() {
        System.out.println(LINE_BREAK);
    }
}
