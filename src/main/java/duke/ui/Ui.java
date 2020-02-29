package duke.ui;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner in;

    /** Defines the constructor and opens the read-in scanner. */
    public Ui() {
        in = new Scanner(System.in);
    }

    /** Display the welcome message. */
    public void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }
    
    
    // command related

    /**
     * Read in the command from the user.
     * 
     * @return Raw user input.
     */
    public String readCommand() {
        String input = in.nextLine();
        return input;
    }

    /**
     * Lists all the tasks inside the given task list.
     * 
     * @param tasks Task list that needs to be presented on the screen.
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        
        if (tasks.getListSize() <= 0) {
            System.out.println("Empty List.");
            return;
        }
        
        for (int i = 1; i <= tasks.getListSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.print(String.format("%d.", i));
            System.out.println(task);
        }
    }

    /**
     * Tells the user that the target task has been marked as done.
     * 
     * @param task Target task.
     */
    public void displayDoneCommandMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(task);
    }

    /**
     * Tells the user that the target task has been added into the task list.
     * 
     * @param task Target task.
     * @param taskQuantity The size of the task list.
     */
    public void displayAddTaskMessage(Task task, int taskQuantity) {
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskQuantity));
    }

    /**
     * Tells the user that the target task has been removed out from the task list.
     * 
     * @param tasks Target task list.
     * @param taskNumber Number of the unwanted task in the list.
     */
    public void displayDeleteTaskMessage(TaskList tasks, int taskNumber) {
        System.out.println("Noted. I've removed this task: ");
        System.out.print("  ");
        System.out.println(tasks.getTask(taskNumber));

        // display the message first, then delete the task
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getListSize() - 1)); 
    }

    /** Tells the user that the task list has been cleared. */
    public void displayClearAllMessage() {
        System.out.println("All tasks cleared.");
    }

    /** Shows the exit message. */
    public void displayExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    
    // exception related

    /** Tells the user that error occurs when loading files. */
    public void displayLoadingError() {
        System.out.println("Something goes wrong when loading files...");
    }

    /** Tells the user that the target list is empty. */
    public void displayEmptyListMessage() {
        System.out.println("Empty List.");
    }

    /** Tells the user that the task number entered is invalid. */
    public void displayInvalidTaskNumberMessage() {
        System.out.println("Please enter a valid task number~");
    }

    /** Tells the user that command entered is invalid. */
    public void displayCommandNotFoundMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Tells the user that the description of certain commands is missing.
     * 
     * @param command Command whose description is missing.
     */
    public void displayEmptyDescriptionMessage(String command) {
        System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", command));
    }

    /** Tells the user that the time of some command is missing. */
    public void displayTimeMissingMessage() {
        System.out.println("Oops! Time is missing!");
    }

    /**
     * Tells the user that some errors have occurred.
     * 
     * @param e Exception that just occurred.
     */
    public void displayErrorMessage(Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }
}
