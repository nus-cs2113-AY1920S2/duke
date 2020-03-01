package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

public class Ui {
    protected String lineSeparator = "--------------------------------------------";
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;

    }

    // Helper function for printing line separator
    public void printLineSeparator(){
        System.out.println(lineSeparator);
    }

    // Prints out greeting for user
    public void greeting(){
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        printLineSeparator();
    }

    // Prints out goodbye message for user
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    // Prints out error loading message
    public void showLoadingError(){
        System.out.println("Error loading from task list. Creating new task list.");
    }

    // Function that prints task
    public void printTask(Task t){
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
    }


    // Prints error invalid command message
    public void invalidCommand(){
        printLineSeparator();
        System.out.println("Invalid command. Please try again! ");
        printLineSeparator();
    }
}
