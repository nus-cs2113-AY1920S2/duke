package duke.utility;


/**
 * Container for user messages.
 */
public class Messages {

    private static final String BULLET = "\t\t\u2023";

    /** General messages */
    public final static String INVALID_FORMAT = "Invalid input for adding a %s.";
    public final static String PROPER_USAGE = System.lineSeparator() + "\t\tExpected format: %s";
    public final static String NOT_A_NUMBER_FOR_TASK = "%s is not a valid number";
    public final static String TASK_OUT_OF_BOUNDS = "The task number %d doesn't exist. There %s only %d %s";
    public final static String CURRENT_TASK_NUMBER = "\tNow you have %d %s in your list";
    public final static String INVALID_COMMAND = "Invalid command, type 'help' to find the supported commands.";

    /** Specific to delete command */
    public final static String DELETE_EMPTY_LIST = "Your list is empty... I cannot delete something that doesn't exist";
    public final static String DELETE_TASK = "Okay! I've removed the following task from your list :) :" +
            System.lineSeparator() + "\t\t%s" + System.lineSeparator();
    public final static String LIST_IS_EMPTY_NOW = "\tYour list is empty now :O";
    public final static String EMPTY_DELETE = "Please enter a number to remove a task from the list.";


    /** Specific to done command */
    public final static String DONE_EMPTY_LIST = "Your list is empty... I cannot mark something that doesn't exist as done >:(";
    public final static String DONE_MARKED_BEFORE = "The task:" + System.lineSeparator() + BULLET + " %s" +
            System.lineSeparator() + "\t" + "has already been marked as done before";
    public final static String DONE_MARKED_SUCCESSFUL = "Okay! Marked this task as done :) :" + System.lineSeparator() + "\t\t%s";
    public final static String EMPTY_DONE= "Please enter a number to mark a task as done.";


    /** Specific to adding a task (to do, event, or deadline) */
    public final static String ADDED_TASK = "Okay! I have added the following task to your list:" +
            System.lineSeparator() + "%s %s" + System.lineSeparator() + "\tNow you have " + "%d %s in your list :)";
    public final static String EMPTY_FIELD = "Failed to add %s. Reason: %s";


    /** Specific to list command */
    public final static String EMPTY_LIST = "List is empty";

}
