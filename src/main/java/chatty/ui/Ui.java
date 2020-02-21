package chatty.ui;

import chatty.task.Task;

import java.util.List;

import static chatty.util.Constants.BOT_NAME;
import static chatty.util.Constants.DOT_CHARACTER;
import static chatty.util.Constants.LINE_BREAK;
import static chatty.util.Constants.SPACE_SEPARATOR;

public class Ui {
    public Ui(){

    }

    public void sendWelcomeMessage() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello from " + BOT_NAME);
        System.out.println("Glad to be at your service!");
        System.out.println(LINE_BREAK);
    }

    public void sendByeMessage() {
        System.out.println("Thanks for chatting with " + BOT_NAME);
        System.out.println("See you again soon!");
    }

    public void listAllTasks(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + DOT_CHARACTER + SPACE_SEPARATOR + task.toString());
        }
    }

    public void sendDefaultResponse() {
        System.out.println("Sorry, I can't help you with that yet");
        System.out.println("I'm " + BOT_NAME);
        System.out.println("How may I help you?");
    }

    public void sendDonePrompt() {
        System.out.println("Let me know which task you would like to mark as done?");
    }

    public void sendTodoPrompt() {
        System.out.println("Sure, let me know what ToDo task you would like to add!");
    }

    public void sendDeadlineTimePrompt() {
        System.out.println("Sure, let me know what Deadline task you would like to add!");
    }

    public void sendEventPrompt() {
        System.out.println("Sure, let me know what Event task you would like to add!");
    }

    public void sendDeletePrompt() {
        System.out.println("Let me know which task you would like to delete?");
    }

    public void sendSpecifyEventTimePrompt() {
        System.out.println("Please specify the time of your event");
    }

    public void sendSpecifyDeadlinePrompt() {
        System.out.println("Please specify the deadline of your task");
    }

    public void sendReadingTaskMessage() {
        System.out.println("Reading tasks from disk...");
    }

    public void sendReadTaskSuccessMessage() {
        System.out.println("Successfully read the following task from file:");
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
