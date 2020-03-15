package duke.ui;

import duke.taskManager.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.constants.Constants.*;

public class DisplayUI {

    private final Scanner in;

    private final PrintStream out;

    public DisplayUI() {
        this(System.in, System.out);
    }

    /**
     * Constructor for Display UI
     *
     * @param in  Input Stream
     * @param out Print Stream
     */
    public DisplayUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Print Greeting Message when program launches
     */
    public void showStartMessages() {
        printToUser(
                DIVIDER,
                LOGO,
                VERSION,
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER,
                FUNCTION_LIST);
    }

    /**
     * Show the help functions table
     */
    public void showFunctionList() {
        printToUser(
                FUNCTION_LIST);
    }

    /**
     * Show the task being deleted currently
     *
     * @param tasks       Array of tasks stored
     * @param tasksNumber Current task number that is being deleted
     */
    public void showDeletedTasks(ArrayList<Task> tasks, int tasksNumber) {
        printToUser(
                TASK_DELETED_MESSAGE,
                INDENT + tasks.get(tasksNumber),
                TASKNUMBER_LEFT_MESSAGE + (tasks.size() - 1),
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    /**
     * Show the task that is being marked as done
     * @param tasks Array of tasks stored
     * @param taskNumber The task number that is currently being marked
     */
    public void showDoneTask(ArrayList<Task> tasks, int taskNumber) {
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                TASK_DONE_MESSAGE,
                INDENT + tasks.get(taskNumber),
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    /**
     * Show all the available tasks in the list
     */
    public void showAllTask() {
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                TASK_LEFT_MESSAGE);
    }

    /**
     * Show the task that is being added into the list
     * @param addedTask Task that is currently being added into list
     * @param tasks Array of tasks stored
     */
    public void showAddedTask(Task addedTask, ArrayList<Task> tasks) {
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                TASK_ADDED_MESSAGE + addedTask,
                TASKNUMBER_LEFT_MESSAGE + tasks.size(),
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    /**
     * Show when the list is empty
     */
    public void showEmptyTaskList() {
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                EMPTY_TASKS_MESSAGE,
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    /**
     * Show when invalid command is given
     */
    public void showErrorInput() {
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                INVALID_COMMAND_MESSAGE,
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    /**
     * A printer to print the String of message
     *
     * @param message String of message to be printed
     */
    public void printToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }
}