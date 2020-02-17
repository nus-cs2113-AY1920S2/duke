package Duke;

import Duke.TaskTypes.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represent the UI that the user uses to interact with Duke. Contains the display methods and user input methods.
 */
public class Ui {

    private Scanner myInput;
    public Ui() {
    }

    private Scanner initializeScanner() {
        return new Scanner(System.in);
    }

    private String getUserInput(Scanner myInput) {
        return myInput.nextLine();
    }

    /**
     * Display the welcome screen
     */
    public void displayHello() {
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
    public void displayGoodbye() {
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
    }

    /**
     * Display to user what the exact task is added into the task list
     * @param newTask the {@link Task} object of containing the task itself
     * @see Task
     */
    public void displayTaskAdded(Task newTask) {
        System.out.println("Got it. I've added this task: " + newTask.toString());
    }

    /**
     * Ui element to read and return the user input
     * @return the string containing the user input
     */
    public String getUserInput() {
        myInput = initializeScanner();
        return getUserInput(myInput);
    }

    /**
     * Just show a message indicating it is out of range
     * <p></p>
     * <p>
     * Only used tp display a message when the user wants to access a non-existent element
     * </p>
     */
    public void displayOutOfRange() {
        System.out.println("Out of range");
    }

    /**
     * Display task based on the value of <code>i</code>, which is the task number
     * @param i task number
     * @param tasks the list of tasks to be seen
     * @see TaskList
     */
    public void displayEachTask(int i, TaskList tasks) {
        System.out.println(
                Integer.toString(i + 1) + ". " + tasks.getTaskList().get(i).toString());
    }

    /**
     * Confirmation message of task being marked as done
     * @param tasks the list of tasks
     * @param taskListNumber task number that was marked as done
     * @see TaskList
     */
    public void displayTaskMarkedAsDone(TaskList tasks, int taskListNumber) {
        System.out.println(
                "Nice! I marked this as done: " + tasks.getTaskList().get(taskListNumber - 1).toString());
    }

    /**
     * Just a confirmation message to show that a task is successfully removed
     * @param removedTask name of task removed
     * @param currentNumberOfTasks number of tasks left in the list upon successful task removal
     */
    public void displayTaskRemoved(String removedTask, int currentNumberOfTasks) {
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
    public void displayPrompt() {
        System.out.println("What else do you want to do?");
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
