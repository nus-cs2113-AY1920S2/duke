package duke;

import duke.tasktypes.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the UI that the user uses to interact with Duke. Contains the display methods and user input methods.
 */
public class Ui {


    private static final String LINE_SEPARATOR = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String GREETINGS = "(°▽°)/\n";
    private static final String GOOD_BYE = "(x_x)⌒☆\n";
    private static final String ANNOYED = "(」°ロ°)」\n";
    private static final String PLEASED = "(ﾉ´ヮ`)ﾉ *:ﾟ \n";
    private static final String SURPRISED = "ヽ(・∀・)ﾉ \n";
    private static final String CONFUSED = "╮(￣ω￣;)╭ \n";
    private static final String SAD = "(；￣Д￣) \n";
    private static final String HAPPY = "ヽ(*・ω・)ﾉ \n";
    private static final String SIGH = "＼(￣▽￣)／ \n";
    private static final String SURPRISED_2 = "(*´▽`*) \n";
    private Scanner myInput;

    public Ui() {
    }

    /**
     * Helps to format and display an exception message
     * @param e the exception to be used
     * @param s the string to be shown with the exception message
     */
    public static void displayGenericException(Exception e, String s) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Exception occurred: " + e + s);
    }

    /**
     * The exception message shown when a task fails to be imported from saved data
     * @param s the task that failed to be imported
     */
    public static void displayErrorImportingTask(String s) {
        System.out.println("Error in importing this task! Said task information is: " + System.lineSeparator() +
                s);
    }

    /**
     * Display the welcome screen
     */
    public static void displayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello from\n" + logo);
        System.out.println(GREETINGS);
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

    }

    /**
     * Display the exit screen. Shown when the user exits
     */
    public static void displayGoodbye() {
        System.out.println(System.lineSeparator());
        System.out.println(LINE_SEPARATOR);
        System.out.println(GOOD_BYE);
        System.out.println("Bye! Hope to see you again soon! Maybe next time!");
        System.out.println(LINE_SEPARATOR);

    }

    /**
     * Display to user what the exact task is added into the task list
     * @param newTask the {@link Task} object of containing the task itself
     * @see Task
     */
    public static void displayTaskAdded(Task newTask) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(PLEASED);
        System.out.println("Got it. I've added this task: " + newTask.toString());
    }


    /**
     * Display task based on the value of <code>i</code>, which is the task number
     * @param i     task number
     * @param tasks the list of tasks to be seen
     * @see TaskList
     */
    public static void displayEachTask(int i, TaskList tasks) {
        System.out.println(
                (i + 1) + ". " + tasks.getTaskList().get(i).toString());
    }

    /**
     * Confirmation message of task being marked as done
     * @param tasks          the list of tasks
     * @param taskListNumber task number that was marked as done
     * @see TaskList
     */
    public static void displayTaskMarkedAsDone(TaskList tasks, int taskListNumber) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(HAPPY);
        System.out.println(
                "Nice! I marked this as done: " + tasks.getTaskList().get(taskListNumber - 1).toString());
    }

    /**
     * Just a confirmation message to show that a task is successfully removed
     * @param removedTask          name of task removed
     * @param currentNumberOfTasks number of tasks left in the list upon successful task removal
     */
    public static void displayTaskRemoved(String removedTask, int currentNumberOfTasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(SIGH);
        System.out.println(
                "Noted. I removed this task: "
                        + System.lineSeparator()
                        + removedTask
                        + System.lineSeparator()
                        + "Now you have "
                        + currentNumberOfTasks
                        + " tasks in the list");
    }

    /**
     * A message to prompt the user to enter the next command
     */
    public static void displayPrompt() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("What else do you want to do?");

    }

    /**
     * A message that shows no tasks corresponds to the message string used to search
     */
    public static void displayNoResultReturned() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(CONFUSED);
        System.out.println("This word/sentence is not found!");
    }

    /**
     * The method helps to show every task present in duke so far
     * @param tempResults the <code>ArrayList</code> of tasks
     */
    public static void displayObtainedResults(ArrayList<String> tempResults) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(SURPRISED);
        System.out.println(tempResults.size() + " task found! Here are the matching tasks in your list:");
        for (String resultFound : tempResults) {
            System.out.println(resultFound);
        }
    }

    /**
     * Another exception message formatter
     * @param m the exception to be used
     */
    public static void displayExceptionError(Exception m) {
        System.out.println(ANNOYED);
        System.out.println("Exception occurred: " + m);
    }

    /**
     * Display that there is no tasks present in the list of tasks yet
     */
    public static void displayNothingInList() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(SAD);
        System.out.println("Nothing yet");
    }

    /**
     * The message shown for either <code>DELETE</code> or <code>DONE</code> if there is no number field given
     * @param nameOfTask the name of the task that was checked
     * @return the message to indicate the number field is empty
     */
    public static String displayMissingNumberFieldError(String nameOfTask) {
        return nameOfTask.toUpperCase() + "'s number field is empty!";
    }

    /**
     * The message shown if the date is formatted wrongly
     * @return the message to indicate the date is formatted wrongly
     */
    public static String displayDateUnableToBeParsedError() {
        return "Date cannot be parsed! Make sure the format is correct! Format: yyyy-mm-dd";
    }

    /**
     * The message shown if the date is missing despite duke expecting for it
     * @return the message to indicate that time is missing
     */
    public static String displayMissingTimeError() {
        return "Missing date!";
    }

    /**
     * The message shown if the slash word is missing despite duke expecting for it
     * @return the message to indicate the lack of slash word
     */
    public static String displayMissingSlashWordError() {
        return "Missing slash word!";
    }

    /**
     * The message shown if the input given lacks the description
     * @return the message to indicate the lack of description
     */
    public static String displayMissingDescriptionError() {
        return "Missing description!";
    }

    /**
     * The message shown if the input have a task that duke does not support
     * @return the message to indicate the input is invalid due to the task type being unsupported
     */
    public static String displayInputInvalidError() {
        return "Input is invalid. No such task";
    }

    /**
     * The message show if the slash word given is wrong
     * @return the message to indicate the task's slash word is wrong
     */
    public static String displayWrongSlashWord() {
        return "Wrong slash word!" + System.lineSeparator() +
                "for EVENT: the slash word can only be 'at' or 'on'" + System.lineSeparator() +
                "for DEADLINE: the slash word can only be 'by' ";
    }

    /**
     * The message shown if the <code>DONE</code> or <code>DELETE</code> number is not a number
     * @param taskType whether if the task checked is a <code>DONE</code> or <code>DELETE</code>
     * @return the message to indicate the number give is not a number
     */
    public static String displayInputNotANumber(String taskType) {
        return taskType + "'s number field does not contain a number!";
    }

    /**
     * The message shown if the <code>DONE</code> or <code>DELETE</code> number does not correspond to any task
     * number in the task list
     * @return the message to indicate that the number given is out of the task list range
     */
    public static String displayTaskNumberOutOfRange() {
        return "Task number chosen is out of range!";
    }

    /**
     * Used by the <code>LIST</code> command to show the start of the list
     */
    public static void displayStartOfList() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(SURPRISED_2);
        System.out.println("Here is the list of tasks:");
    }

    /**
     * Just a method to show the ~~~~~ lines
     */
    public static void displayLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Creates the scanner to read input
     * @return the scanner itself
     */
    private Scanner initializeScanner() {
        return new Scanner(System.in);
    }

    /**
     * Ui element to read and return the user input
     * @return the string containing the user input
     */
    public String getUserInput() {
        myInput = initializeScanner();
        return myInput.nextLine();
    }

    /**
     * Close the input scanner
     * <p></p>
     * <p>
     * Only used once when Duke ends
     * </p>
     */
    public void closeScanner() {
        myInput.close();
    }
}
