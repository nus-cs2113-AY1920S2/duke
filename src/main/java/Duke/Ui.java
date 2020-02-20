package Duke;

import Duke.TaskTypes.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the UI that the user uses to interact with Duke. Contains the display methods and user input methods.
 */
public class Ui {

    private Scanner myInput;

    public Ui() {
    }

    public static void displayGenericException(Exception e, String s) {
        System.out.println("Exception occurred: " + e + s);
    }

    public static void displayErrorImportingTask(String s) {
        System.out.println("Error in importing this task! Task starts with: " +
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
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
    }

    /**
     * Display the exit screen. Shown when the user exits
     */
    public static void displayGoodbye() {
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
    }

    /**
     * Display to user what the exact task is added into the task list
     * @param newTask the {@link Task} object of containing the task itself
     * @see Task
     */
    public static void displayTaskAdded(Task newTask) {
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
        System.out.println(
                "Nice! I marked this as done: " + tasks.getTaskList().get(taskListNumber - 1).toString());
    }

    /**
     * Just a confirmation message to show that a task is successfully removed
     * @param removedTask          name of task removed
     * @param currentNumberOfTasks number of tasks left in the list upon successful task removal
     */
    public static void displayTaskRemoved(String removedTask, int currentNumberOfTasks) {
        System.out.println(
                "Noted. I removed this task: "
                        + System.lineSeparator()
                        + removedTask
                        + System.lineSeparator()
                        + "Now you have "
                        + currentNumberOfTasks
                        + " tasks in the list"
                        + System.lineSeparator());
    }

    /**
     * A message to prompt the user to enter the next command
     */
    public static void displayPrompt() {
        System.out.println("What else do you want to do?");
    }

    public static void displayNoResultReturned() {
        System.out.println("This word/sentence is not found!");
    }

    public static void displayObtainedResults(ArrayList<String> tempResults) {
        System.out.println(tempResults.size() + " task found! Here are the matching tasks in your list:");
        for (String resultFound : tempResults) {
            System.out.println(resultFound);
        }
    }

    public static void displayExceptionError(Exception m) {
        System.out.println("Exception occurred: " + m);
    }

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
