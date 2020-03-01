package ui;
import task.TaskList;
import exception.DukeException;

import java.util.Scanner;

public class Ui {

    private static final String LS = System.lineSeparator();
    private static final String LINE_PREFIX = "|| ";
    private static final String DIVIDER = "===================================================";
    private final Scanner in = new Scanner(System.in);;
    private static final String ERROR_MESSAGE_START = "☹ OOPS!!! ";

    public void showWelcome() {
        String logo = "***TASK MANAGER***";
        System.out.println(LINE_PREFIX + "Hello! I am " + logo);
        System.out.println(LINE_PREFIX + "What would you like to do?");
    }

    public void displayExit() {
        System.out.println(LINE_PREFIX + "Bye. Hope to see you soon!");
    }

    public String readCommand() {
        System.out.println(LINE_PREFIX + "Enter command: ");
        String userInput = in.nextLine();

        System.out.println("[Command entered: " + userInput + "]");
        return userInput;
    }

    public void printNewTask(TaskList tasks) {
        System.out.println(LINE_PREFIX + "New task added: ");
        System.out.println(LINE_PREFIX + " " + tasks.getLatestTask());
        System.out.println(LINE_PREFIX + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void listTasks(TaskList tasks) throws DukeException {
        System.out.println(LINE_PREFIX + "Here are your tasks:");
        for (int j = 0; j < tasks.getSize(); j++) {
            System.out.println(LINE_PREFIX + (j+1) + ". " + tasks.getTask(j));
        }
    }

    public void showError(DukeException error) {
        System.out.println(LINE_PREFIX + ERROR_MESSAGE_START + error.getMessage());
    }

    public void showLoadingError(){
        System.out.println(LINE_PREFIX + ERROR_MESSAGE_START + "Error loading page.");
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void displayDeleteTask(TaskList tasks, int taskNumber) throws DukeException {
        System.out.println(LINE_PREFIX + "Noted. I've removed this task:");
        System.out.println(LINE_PREFIX + " " + tasks.getTask(taskNumber));
        System.out.println(LINE_PREFIX + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
    }

    public void displayDoneTask(TaskList tasks, int taskNumber) throws DukeException {
        System.out.println(LINE_PREFIX + "Nice! I've marked this task as done:");
        System.out.println(LINE_PREFIX + " " + tasks.getTask(taskNumber));
    }
}
