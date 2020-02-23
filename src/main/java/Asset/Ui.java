package Asset;

import Tasks.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This class handles with the interaction with User.
 */
public class Ui  {
    public static final String LINE = "\t__________________________________________________________";
    public static final String[] COMMAND= {"todo", "deadline", "event", "done", "bye", "list", "help"};
    public static final String WRONG_INPUT="\t ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n" +
            "\t Input command is wrong. Enter \"help\" for list of accepted\n\t commands";
    public static final String LIST_EMPTY= "\t Oops! No task has been assigned yet! Please enter a task!";
    public static final String MISSING_FILE = "Please check if data.txt exist!";
    public static final String MATCHING_TASK= "\t Here are the matching tasks in your list!";
    public static final String NO_MATCHING_TASK = "\t Oops! No such task can be found!";
    public static final String YES_OR_NO_ONLY = "\t I'm sorry but the options are only Y for YES or N for NO!";

    private final Scanner in;
    private final PrintStream out;


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    /**
     * This methods gets the User input.
     */
    public String getUserIn(){
        return in.nextLine();
    }
    /**
     * This method prints the welcome message to User.
     */
    public void printWelcomeMessage() {
        String banner = "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String logo = "\t~~~~~~~~~~~~~~~________   ___  ________   ___~~~~~~~~~~~~\n" +
                "\t~~~~~~~~~~~~~~|\\   ___  \\|\\  \\|\\   ___  \\|\\  \\~~~~~~~~~~~\n" +
                "\t~~~~~~~~~~~~~~\\ \\  \\\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\~~~~~~~~~~\n" +
                "\t~~~~~~~~~~~~~~~\\ \\  \\\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\~~~~~~~~~\n" +
                "\t~~~~~~~~~~~~~~~~\\ \\  \\\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\~~~~~~~~\n" +
                "\t~~~~~~~~~~~~~~~~~~\\ \\__\\\\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\__\\~~~~~~\n" +
                "\t~~~~~~~~~~~~~~~~~~\\|__| \\|__|\\|__|\\|__| \\|__|\\|__|~~~~~~~\n";
        out.println(LINE);
        out.println("\t" + "Hello from\n" + banner + logo + banner);
        out.println("\t" + "What can I do for you?");
        out.println(LINE);
    }
    /**
     * This method prints constant LINE
     */
    public void printLine(){
        out.println(LINE);
    }
    /**
     * This method prints all the error message thrown in Duke.run().
     */
    public void printError(String message){
        out.println(message);
    }
    /**
     * This method informs User that a new Task class has been created and added to list
     */
    public void printAddTask(ArrayList<Task> l1, Task task){
        out.println("\t Got it. I've added this task:");
        out.println("\t   "+task.toString());
        out.println("\t Now you have " + l1.size()+ " tasks in the list.");
    }
    /**
     * This method prints good bye message to User.
     */
    public void printGoodByeMessage(){
        out.println("\t Bye.Hope to see you again soon!");
    }
    /**
     * This method informs User that stated Task has been marked as done.
     */
    public void printDone(Task task){
        out.println("\t Nice! I've marked this task as done:");
        out.println("\t   "+task.toString());
    }
    /**
     * This method informs User that stated Task has been deleted.
     */
    public void printDelete(Task task, ArrayList<Task> l1){
        out.println("\t Noted. I've removed this task: ");
        out.println("\t   " + task.toString());
        out.println("\t Now you have " + l1.size() + " tasks in the list.");
    }
    /**
     * This method informs User that all Tasks has been marked as done.
     */
    public void printDoneAll(ArrayList<Task> l1){
        out.println("\t All tasks have been marked as done!");
            for (int i = 0; i < l1.size(); i++) {
                int count = i + 1;
                Task task = l1.get(i);
                out.println("\t " + count + "." + task.toString());
            }
    }
    /**
     * This method informs User that all Tasks has been deleted.
     */
    public void printDeleteAll(){
        out.println("\t All tasks have been deleted!");
    }
    /**
     * This method confirms with User if they want all Tasks to be deleted.
     */
    public void confirmDeleteAll(){
        out.println("\t Are you sure that you want ALL tasks to be deleted?");
        out.println("\t Type Y for Yes and N for No");
    }
    /**
     * This method informs User that the delete ALL command is ignored.
     */
    public void ignoreDeleteAll(){
        out.println("\t List was not modified!");
        out.println("\t Enter \"list\" to ensure all tasks are still in the list!");
    }
    /**
     * This method shows User of all available Tasks.
     * @param l1 is the current list of available Tasks.
     * If l1 is empty, method will inform user that no Tasks
     * are available.
     */
    public void printList(ArrayList<Task> l1){
        if(l1.isEmpty()){
            out.println(LIST_EMPTY);
        }else {
            out.print("\t Here are the tasks in your list:\n");
            for (int i = 0; i < l1.size(); i++) {
                int count = i + 1;
                Task task = l1.get(i);
                out.println("\t " + count + "." + task.toString());
            }
        }
    }
    /**
     * This method informs User that command entered is not supported by Duke.
     */
    public void printWrongInput(){
        out.println(WRONG_INPUT);
    }

    public void printYesOrNoOnly(){
        out.println(YES_OR_NO_ONLY);
    }
    /**
     * This method lists the supported commands for User.
     */
    public void printHelp(){
        out.println("\t "+ Arrays.toString(COMMAND));
    }
    /**
     * This method informs User that data.txt is missing.
     */
    public void showLoadingError(){
        out.println(MISSING_FILE);
    }
    /**
     * This method shows User the list of Tasks that matches with
     * User search key.
     *
     * @param l1 is the list of all Tasks.
     * @param l2 is the list of matching Tasks.
     */
    public void printFindList(ArrayList<Task> l1, ArrayList<Integer> l2){
        if(l2.isEmpty()){
            out.println(NO_MATCHING_TASK);
        }else {
            out.println(MATCHING_TASK);
            for (int i = 0; i < l2.size(); i++) {
                int count = i + 1;
                Task task = l1.get(l2.get(i));
                out.println("\t " + count + "." + task.toString());
            }
        }
    }
    /**
     * This method closes the System.in class of Duke.
     */
    public void close(){
        this.in.close();
    }
}




