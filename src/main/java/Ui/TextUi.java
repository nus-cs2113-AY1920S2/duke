package Ui;
import TaskList.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a text formatter to print objects
 */
public class TextUi {
    public TextUi(){}

    /**
     * Prints the opening message of the program
     */
    public void printWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ";
        System.out.println(logo + " says hello :)");
        System.out.println("\nWhat can i do for you?");
    }

    /**
     * Prints the exiting message of the program
     */
    public void printExitMsg() {
        System.out.println("Bye bye! Talk to me again soon!");
    }

    /**
     * Reading of user input
     *
     * @return the user's input
     */
    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        String cmd = myObj.nextLine();
        return cmd;
    }

    /**
     * Prints the error message of the program when
     * loading an invalid file into list of Tasks
     */
    public void showLoadingError() {
        System.out.println("Duke has encountered a loading error :( Let's create one!");
    }

    /**
     * Prints the error message of the program when
     * there exist an error in loading/saving from/to file
     */
    public void printIOError() {
        System.out.println("IO saving tasklist error occured!");
    }

    /**
     * Prints the error message of the program when
     * the user has given a command that cannot be processed
     */
    public void printFormatError() {
        System.out.println("I cannot understand the command, please check again.");
    }

    /**
     * Prints the content of the list of Tasks
     *
     * @param taskList the list of Tasks
     */
    public void printTaskList(ArrayList<Task> taskList){
        if (taskList.size() == 0) {
            System.out.println("There are currently nothing to do! Add something!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).printObject());
        }
    }
}
