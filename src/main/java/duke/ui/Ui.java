package duke.ui;

import duke.data.TaskList;
import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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

    public void bye(){
        System.out.println(CUTTING_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(CUTTING_LINE);
    }

    public String getUserCommand(){
        return in.nextLine();
    }

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

    public void printTask(Task newTask, TaskList tasks, String taskType) {
        out.println(CUTTING_LINE);
        out.println("\tGot it. I've "+ taskType+ " this task:");
        out.println("\t" + newTask.toString());
        out.printf("\tNow you have %d task(s) in the list\n", tasks.size());
        out.println(CUTTING_LINE);
        out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

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
