package duke.ui;

import duke.common.DukeException;
import duke.tasklist.TaskList;
import duke.tasklist.task.Task;

import java.util.Scanner;

import static duke.common.Constants.LINE_BREAK;
import static duke.common.Constants.LOGO;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static Scanner in = new Scanner(System.in);

    public Ui() {
        System.out.println("Hello from\n" + LOGO);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm duke.Duke");
        System.out.println("\tWhat can I do for you?");
        showLine();
        System.out.println();
    }

    /**
     * Generates and prints the goodbye message upon the end of the application.
     */
    public void showGoodByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Displays list of tasks and if they are done.
     *
     * @param tasks store the task list.
     * @throws DukeException If there is no tasks, throws the exception.
     */
    public void showListOfTasks(TaskList tasks) throws DukeException {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getATask(i));
        }
    }

    /**
     * Displays prompt message that a task is marked as done.
     * Displays the task that is marked done.
     *
     * @param tasks store the task list.
     * @param doneCount the task that is done.
     */
    public void showMarkAsDoneMessage(TaskList tasks, int doneCount) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  yes! " + tasks.getATask(doneCount).getDescription());
    }

    /**
     * Displays prompt message that a task is deleted.
     * Displays the task that is deleted and the task count.
     *
     * @param deleteTask the task that is deleted.
     * @param tasks stored the taskList.
     */
    public void showDeleteMessage(Task deleteTask, TaskList tasks) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + deleteTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays prompt message that a new task is added in the task list.
     * Displays the newly added task and current task count.
     *
     * @param tasks store the taskList.
     */
    public void showAddTaskMessage(TaskList tasks) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks.getATask(tasks.size() - 1));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
    }

    public void showLoadingError() {
        System.out.println("\tCreating back up file in the hard disk...");
    }

    public void showError(String error) {
        System.out.println(error);
    }
}

