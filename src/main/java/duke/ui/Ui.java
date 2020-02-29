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
    public static final String TAB = "\t";

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
        printOutputTail();
    }

    /** The information shows to the user in the end. */
    public void printOutputTail() {
        out.println(CUTTING_LINE);
        out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    /**Byes when the user issues to exit. */
    public void bye(){
        out.println(CUTTING_LINE);
        out.println("\tBye. Hope to see you again soon!");
        out.println(CUTTING_LINE);
    }

    /**
     * Reads the text command entered by the user.
     * @return command (full line) entered by the user.
     */
    public String getUserCommand(){
        return in.nextLine();
    }

    /**
     * Prints the error gotten in Duke Exception.
     */
    public void showError(String e){
        out.println(CUTTING_LINE);
        out.println("\t☹ OOPS!!! " + e);
        printOutputTail();
    }

    /**
     * Prints the added or deleted task and show the new size of task list.
     * @param newTask the added or deleted task.
     * @param tasks the task list.
     * @param taskType  the task type of this command.
     */
    public void printTask(Task newTask, TaskList tasks, String taskType) {
        out.println(CUTTING_LINE);
        out.println("\tGot it. I've "+ taskType+ " this task:");
        out.println(TAB + newTask.toString());
        out.printf("\tNow you have %d task(s) in the list\n", tasks.size());
        printOutputTail();
    }

    /**
     * Prints the modified task.
     * @param newTask the modified task.
     * @param taskType  the task type of this command.
     */
    public void printTask(Task newTask, String taskType) {
        out.println(CUTTING_LINE);
        out.println("\tGot it. I've "+ taskType+ " this task:");
        out.println(TAB + newTask.toString());
        printOutputTail();
    }

    /**
     * Prints one task to the user.
     * @param task the task to print.
     * @param ID the task ID.
     */
    public void printOneTask(Task task, int ID) {
        out.println(TAB + ID + "." + task.toString());
    }

    /**
     * Prints some information head.
     * @param infoHead some information depends on different task type
     */
    public void printInfoHead(String infoHead) {
        out.println(CUTTING_LINE);
        out.println(TAB +infoHead);
    }

}
