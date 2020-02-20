package duke.ui;

import duke.data.TaskList;
import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {
    public static final String CUTTING_LINE = "    ____________________________________________________________";
    public static final String LOGO = "\t _____    _____   __   _   ______  _____  \n"
            + "\t|  _  \\  | ____| |  \\ | | |___  / /  _  \\ \n"
            + "\t| |_| |  | |__   |   \\| |    / /  | | | | \n"
            + "\t|  _  /  |  __|  | |\\   |   / /   | | | | \n"
            + "\t| | \\ \\  | |___  | | \\  |  / /__  | |_| | \n"
            + "\t|_|  \\_\\ |_____| |_|  \\_| /_____| \\_____/ \n";

    private Scanner in;
    private PrintStream out;

    public Ui() {
       this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /** Greets when the user opens the application. */
    public void greet(){
        out.println(CUTTING_LINE);
        out.println(LOGO);
        out.println("\tHello! I'm Renzo");
        out.println("\tWhat can I do for you?");
        out.println(CUTTING_LINE);
        out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public void printInfoHead() {
        out.println(CUTTING_LINE);
        out.println("\tHere are the tasks in your list:");
    }

    public void printOutputTail() {
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    /**Byes when the user issues to exit. */
    public void bye(){
        System.out.println(CUTTING_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(CUTTING_LINE);
    }

    /**
     * Reads the text command entered by the user.
     * @return command (full line) entered by the user.
     */
    public String getUserCommand(){
        return in.nextLine();
    }

    /**
     * Print the error gotten in Duke Exception.
     */
    public void showError(String e){
        out.println(CUTTING_LINE);
        out.println("\t☹ OOPS!!! " + e);
        out.println(CUTTING_LINE);
        out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void printIdOutOfRangeError() {
        System.out.println(CUTTING_LINE);
        System.out.println("\tWrong Number!");
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command again or enter \"bye\" to exit:");
    }

    /**
     * Print the added or deleted task and show the new size of task list.
     * @param newTask the added or deleted task.
     * @param tasks the task list.
     * @param taskType  the task type of this command.
     */
    public void printTask(Task newTask, TaskList tasks, String taskType) {
        out.println(CUTTING_LINE);
        out.println("\tGot it. I've "+ taskType+ " this task:");
        out.println("\t" + newTask.toString());
        out.printf("\tNow you have %d task(s) in the list\n", tasks.size());
        out.println(CUTTING_LINE);
        out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    /**
     * Print the modified task.
     * @param newTask the modified task.
     * @param taskType  the task type of this command.
     */
    public void printTask(Task newTask, String taskType) {
        out.println(CUTTING_LINE);
        out.println("\tGot it. I've "+ taskType+ " this task:");
        out.println("\t" + newTask.toString());
        boolean isAdd = taskType.equalsIgnoreCase("added");
        out.println(CUTTING_LINE);
        out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public void printOneTask(Task task, int ID) {
        out.println("\t"+ ID + "." + task.toString());
    }

    public void printInfoHead(String infoHead) {
        out.println(CUTTING_LINE);
        out.println(infoHead);
    }

}
