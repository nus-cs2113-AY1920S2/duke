package duke;

import duke.task.Task;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles all input and output of Duke
 */
public class Ui {
    public static final String DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String DIVIDER = "_________________________________________________";
    public static final String LS = System.lineSeparator();
    public static final String NO_TASKS_MESSAGE = "You have no tasks right now though";
    public static final String HAVE_TASKS_MESSAGE = "Quite a few tasks you got there";
    public static final String NO_MATCHING_TASKS_MESSAGE = "Hmm... Seems like no tasks matched your query";
    public static final String FOUND_MATCHING_TASKS_MESSAGE = "Are these what you're looking for?";
    public static final String FIND_INSUFFICIENT_ARGS_MESSAGE = "Please specify a string to search for";
    public static final String TASK_INDEX_OUT_OF_RANGE_MESSAGE = "Please specify a task number between 1 and ";
    public static final String TASK_MARKED_AS_DONE_MESSAGE = "Well, that's one task down" + LS + "  ";
    public static final String TASK_ALREADY_DONE_MESSAGE = "That task is already done";
    public static final String GENERIC_ERROR_MESSAGE_PREFIX = "☹ OOPS!!! ";
    public static final String TODO_INSUFFICIENT_ARGS_MESSAGE = "The description of a todo cannot be empty";
    public static final String DEADLINE_INSUFFICIENT_ARGS_MESSAGE =
            "The description and date of a deadline cannot be empty";
    public static final String EVENT_INSUFFICIENT_ARGS_MESSAGE = "The description and date of an event cannot be empty";
    public static final String UNKNOWN_COMMAND_NAME_MESSAGE = "I'm not very sure what that means...";
    public static final String TASK_NUMBER_NOT_SPECIFIED_MESSAGE = "Please specify a task number";
    public static final String TASK_NUMBER_NOT_INTEGER_MESSAGE = "Please specify an integer for the task number";
    public static final String UNKNOWN_STORAGE_FORMAT_MESSAGE =
            "Unknown save file format encountered"
            + LS
            + "Exit now to manually fix and retain saved data";
    public static final String INVALID_DATE_FORMAT_MESSAGE  =
            "Please specify the date and time using the format DD/MM/YY HHMM";
    public static final String INVALID_EVENT_DATE_FORMAT_MESSAGE  =
            "Please specify the starting and ending date and time using the format DD/MM/YY HHMM - HHMM";
    public static final String INVALID_START_AND_END_TIME_MESSAGE =
            "The starting time should be before the ending time";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Prints a line divider to better format Duke's output.
     */
    public void showDivider() {
        out.println(DIVIDER);
    }


    /**
     * Prints all messages, separated by a newline.
     * @param messages strings to be shown to the user
     */
    public void showToUser(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }

    /**
     * Prints Duke's welcome message.
     */
    public void showWelcomeMessage() {
        showDivider();
        showToUser("This is", DUKE_LOGO, "How can I help you today?");
        showDivider();
    }

    /**
     * Prints a message to the user before Duke exits
     */
    public void showByeMessage() {
        showToUser("Bye then");
        showDivider();
    }

    /**
     * Shows the user that a task has been successfully added to the TaskList.
     * @param addedTask the Task object that has been added
     * @param numTasks number of tasks currently in the TaskList
     */
    public void showAddedTaskMessage(Task addedTask, int numTasks) {
        showToUser("Task added:",
                "  " + addedTask,
                "You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in the list"
        );
    }

    /**
     * Shows the user that a task has been successfully deleted from the
     * TaskList.
     *
     * @param deletedTask the Task object that has been deleted
     * @param numTasks number of tasks currently in the TaskList
     */
    public void showDeletedTaskMessage(Task deletedTask, int numTasks) {
        showToUser("Task deleted:",
                "  " + deletedTask,
                "You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in the list"
        );
    }

    /**
     * Shows a prompt to the user, then reads the next line of user input
     * @return line input by the user
     */
    public String getUserCommand() {
        out.print("> ");
        out.flush();
        return in.nextLine().trim();
    }
}
