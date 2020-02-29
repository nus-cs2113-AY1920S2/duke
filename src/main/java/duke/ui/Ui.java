package duke.ui;

import duke.common.DukeException;
import duke.tasklist.TaskList;
import duke.tasklist.task.Task;

import java.util.ArrayList;
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
     * Displays prompt message that a new task is added in the task list.
     * Displays the newly added task and current task count.
     *
     * @param tasks Store the taskList.
     */
    public void showAddTaskMessage(TaskList tasks) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks.getATask(tasks.size() - 1));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Generates and prints the goodbye message upon the end of the application.
     */
    public void showGoodByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Displays tasks that has been found either by keyword/time.
     *
     * @param tasks Stores the taskList.
     * @param findCount  Stores the matching task index.
     */
    public void showFindTask(TaskList tasks, ArrayList<Integer> findCount) {
        if (findCount.size() > 0) {
            System.out.println("\tHere are the matching tasks in your list:");
            for (Integer i : findCount) {
                System.out.println("\t" + (i + 1) + "." + tasks.getATask(i));
            }
        } else {
            System.out.println("\t No matching tasks.");
        }
    }

    /**
     * Displays prompt message that a task is marked as done.
     * Displays the task that is marked done.
     *
     * @param tasks Store the task list.
     * @param doneCount The task that is done.
     */
    public void showMarkAsDoneMessage(TaskList tasks, int doneCount) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  yes! " + tasks.getATask(doneCount).getDescription());
    }

    /**
     * Displays prompt message that a task is deleted.
     * Displays the task that is deleted and the task count.
     *
     * @param deleteTask The task that is deleted.
     * @param tasks Stored the taskList.
     */
    public void showDeleteMessage(Task deleteTask, TaskList tasks) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + deleteTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays all usage and format of this program.
     */
    public void showHelpMessage() {
        System.out.println("\tBelow is what Duke can do for you:");
        System.out.println("\t  todo task : add a task that needs to do");
        System.out.println("\t  deadline task /by time(dddd-mm-yy) : add a task that has a certain deadline");
        System.out.println("\t  event task /at time(dddd-mm-yy) : add a task that happens on a day");
        System.out.println("\t  bye : exit the program");
        System.out.println("\t  check dddd-mm-yy : check deadline/event occuring on that day");
        System.out.println("\t  delete index : delete a task by its number index");
        System.out.println("\t  done index : mark a task as done by its number index");
        System.out.println("\t  find keyword : find a task by its keyword");
        System.out.println("\t  list : list all tasks");
    }

    /**
     * Displays all list of tasks and if they are done.
     *
     * @param tasks Stores taskList.
     * @throws DukeException If there is no tasks in current taskList.
     */
    public void showListOfTasks(TaskList tasks) throws DukeException {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getATask(i));
        }
    }

    public void showLoadingError() {
        System.out.println("\tCreating back up file in the hard disk...");
    }

    public void showError(String error) {
        System.out.println(error);
    }
}

