package ui;
import task.Task;
import task.TaskList;
import exception.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Boundary class to manage inputs and outputs of the application.
 */
public class Ui {

    private static final String LS = System.lineSeparator();
    private static final String LINE_PREFIX = "|| ";
    private static final String DIVIDER = "===================================================";
    private final Scanner in = new Scanner(System.in);;
    private static final String ERROR_MESSAGE_START = "☹ OOPS!!! ";

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        String logo = "***TASK MANAGER***";
        System.out.println(LINE_PREFIX + "Hello! I am " + logo);
        System.out.println(LINE_PREFIX + "What would you like to do?");
    }

    /**
     * Displays the exit message.
     */
    public void displayExit() {
        System.out.println(LINE_PREFIX + "Bye. Hope to see you soon!");
    }

    /**
     * Reads the command to be issued by the user.
     * @return user input
     */
    public String readCommand() {
        System.out.println(LINE_PREFIX + "Enter command: ");
        String userInput = in.nextLine();

        System.out.println("[Command entered: " + userInput + "]");
        return userInput;
    }

    /** Displays the new task added by the user. */
    public void printNewTask(TaskList tasks) {
        System.out.println(LINE_PREFIX + "New task added: ");
        System.out.println(LINE_PREFIX + " " + tasks.getLatestTask());
        System.out.println(LINE_PREFIX + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /** Displays the list of tasks currently in the application. */
    public void listTasks(TaskList tasks) throws DukeException {
        System.out.println(LINE_PREFIX + "Here are your tasks:");
        for (int j = 0; j < tasks.getSize(); j++) {
            System.out.println(LINE_PREFIX + (j+1) + ". " + tasks.getTask(j));
        }
    }

    public void listFoundTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println(LINE_PREFIX + "There is no matching task with the given keyword.");
            return;
        }
        System.out.println(LINE_PREFIX + "Here are the matching tasks in your list:");
        for (int j = 0; j < tasks.size(); j++) {
            System.out.println(LINE_PREFIX + (j+1) + ". " + tasks.get(j));
        }
    }

    public void showError(DukeException error) {
        System.out.println(LINE_PREFIX + ERROR_MESSAGE_START + error.getMessage());
    }

    /** Displays error loading message.*/
    public void showLoadingError(){
        System.out.println(LINE_PREFIX + ERROR_MESSAGE_START + "Error loading page.");
    }

    /** Displays a divider line. */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /** Display the task deleted by the user*/
    public void displayDeleteTask(TaskList tasks, int taskNumber) throws DukeException {
        System.out.println(LINE_PREFIX + "Noted. I've removed this task:");
        System.out.println(LINE_PREFIX + " " + tasks.getTask(taskNumber));
        System.out.println(LINE_PREFIX + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
    }

    /** Display the task done by the user. */
    public void displayDoneTask(TaskList tasks, int taskNumber) throws DukeException {
        System.out.println(LINE_PREFIX + "Nice! I've marked this task as done:");
        System.out.println(LINE_PREFIX + " " + tasks.getTask(taskNumber));
    }
}
