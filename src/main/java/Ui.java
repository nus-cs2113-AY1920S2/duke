import Tasks.Task;
import Tasks.TaskList;

import java.io.Serializable;

/**
 * Stores magic strings as constants to be returned as output.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE_SEPARATOR = "----------------------------------------------";
    public static final String HELP = "1. \"list\" to display To Do List\n"
            + "2. \"done [index]\" to mark as done\n"
            + "3. \"todo\" or \"deadline /by [date/time]\" or \"event /at [date/time]\" to add to list\n"
            + "4. \"delete [index]\"to delete\n"
            + "5. \"help\" to look at possible commands\n"
            + "6. \"find [string]\" to find in your list\n"
            + "7. \"bye\" to exit";
    public static final String EMPTY_LIST = LINE_SEPARATOR + "\nYour list is empty bruhh\n" + LINE_SEPARATOR;
    public static final String NO_NUMBER = LINE_SEPARATOR + "You did not enter a number. Try again!!" + LINE_SEPARATOR;
    public static final String OUT_OF_INDEX = LINE_SEPARATOR
            + "The index you entered does not exist??? Look at ur list properly lah" + LINE_SEPARATOR;
    public static final String EMPTY_STRING = "☹ OOPS!!! The description cannot be empty.";
    public static final String DEFAULT = LINE_SEPARATOR + "\nOOPS!!! I don't understand that, type help for help\n"
            + LINE_SEPARATOR;
    public static final String CLEAR_LIST = LINE_SEPARATOR + "\nYour list has been cleared\n" + LINE_SEPARATOR;


    public static String addTaskMessage(String userInput, TaskList taskListArrayList, Task t) {
        return "Okie dokes, \"" + userInput + "\" has been added to your to do list:\n"
                + "Now there are " + taskListArrayList.getLength() + " task(s) in your list";
    }

    /**
     * Returns a String that prints a message to tell the user that his command to delete a task is done.
     * @param userInput : String that is scanned from the user.
     * @param taskListArrayList : TaskList that contains all the tasks.
     */
    public static String deleteTaskMessage(String userInput, TaskList taskListArrayList) {
        return LINE_SEPARATOR + "Noted with thanks. This task is deleted liao: "
                + userInput + "\nNow there are " + taskListArrayList.getLength() + " task(s) in your list"
                + LINE_SEPARATOR;
    }

    /**
     * Prints a welcome message to the user.
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm Duke, your personal assistant");
        System.out.println("1. \"list\" to display To Do List\n"
                + "2. \"done [index]\" to mark as done\n"
                + "3. \"todo\" or \"deadline /by [date/time]\" or \"event /at [date/time]\" to add to list\n"
                + "4. \"delete [index]\"to delete\n"
                + "5. \"help\" to look at possible commands\n"
                + "6. \"find [string]\" to find in your list\n"
                + "7. \"bye\" to exit");
        System.out.println(LINE_SEPARATOR);
    }

    public static void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static void printBye() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. I hope to see u again soon ^^");
        System.out.println(LINE_SEPARATOR);
    }




}
