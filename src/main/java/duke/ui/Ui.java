package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

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
     * Function that prints out that the command the user typed is not valid
     */
    public void invalidCommand(){
        System.out.println("Invalid command. Please try again! ");
    }
}
