package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the interactions the program has with the user e.g, greetings, goodbyes, and
 * current status of list etc. This class displays messages and other interactions to the user
 * as it uses the program, e.g, "Error! Invalid command" 
 */
public class Ui {
    protected String lineSeparator = "--------------------------------------------";
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private TaskList taskList;

    /**
     * Constructor for the Ui class
     * @param taskList reference/pointer to the taskList
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Represents a line that separates the user's response from the program's output
     * Used for GUI purposes.
     */
    public void printLineSeparator(){
        System.out.println(lineSeparator);
    }

    /**
     * A function that prints out the initial greeting and logo for Duke
     */
    public void greeting(){
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        printLineSeparator();
    }

    /**
     * A function that prints out the goodbye message when the user wants
     * to exit the program.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
        System.exit(0);
    }

    /**
     * Function that prints out error message if unable to load tasks.
     */
    public void showLoadingError(){
        System.out.println("Error loading from task list. Creating new task list.");
    }


    /**
     * Function that prints out the current task and that has been added into
     * the taskList and the new size of the taskList.
     * @param t the task that will be printed and added to taskList.
     */
    public void printTask(Task t){
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
    }

    /**
     * Function that prints out the task that was deleted from the taskList
     * @param t
     */
    public void printDeleted(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printMarkedDone(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Function that prints all the tasks of a taskList
     * @param tasks - array of the tasks to be printed
     */
    public void printList(ArrayList<Task> tasks){
        int taskCounter = 1;
        for(Task t: tasks) {
            System.out.println((taskCounter) + "." + t);
            taskCounter++;
        }
    }

    /**
     * Function that prints out message in case of empty list
     */
    public void printEmptyList(){
        System.out.println("The list is empty!");
    }

    /**
     * Function that prints out that the command the user typed is not valid
     */
    public void invalidCommand(){
        System.out.println("Invalid command. Please try again! ");
    }


    /**
     * Function that prints out the command the user type is invalid
     * becauase the description was missing
     * @param commandType type of command that is invalid
     */
    public void invalidCommand(String commandType) {
        System.out.println("Invalid " + commandType + " command. Make sure there is a correct description!");
    }



}
