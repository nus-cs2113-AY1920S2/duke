package Duke.Asset;

import Duke.Tasks.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class handles with the interaction with User.
 */
public class Ui  {
    public static final String LINE = "\t__________________________________________________________";
    public static final String WRONG_INPUT="\t ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n" +
            "\t Input command is wrong. Enter \"help\" for list of accepted\n\t commands";
    public static final String LIST_EMPTY= "\t Oops! No task has been assigned yet! Please enter a task!";
    public static final String MISSING_FILE = "\t Data file \"data.txt\" is missing!";
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
     * @return this returns the User input.
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
     * @param message This is the first argument of this method. It is
     *                the error message thrown by exceptions caught
     *                in Duke.run
     */
    public void printError(String message){
        out.println(message);
    }
    /**
     * This method informs User that a new Task class has been created and added to list
     * @param l1 This is the first argument of this method. It is the list of current
     *           Tasks in Duke.
     * @param task This is the second argument. This is the task that is to be printed to inform
     *             user that the stated task has just been added.
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
     * @param task This is the only argument for this method. It is the
     *             task that User wants to mark as done.
     */
    public void printDone(Task task){
        out.println("\t Nice! I've marked this task as done:");
        out.println("\t   "+task.toString());
    }
    /**
     * This method informs User that stated Task has been deleted.
     * @param task  This is the first argument of this method. It is
     *              the task that the User wants to delete.
     * @param l1    This is the second argument of this method. It is
     *              the current list of Tasks.
     */
    public void printDelete(Task task, ArrayList<Task> l1){
        out.println("\t Noted. I've removed this task: ");
        out.println("\t   " + task.toString());
        out.println("\t Now you have " + l1.size() + " tasks in the list.");
    }
    /**
     * This method informs User that all Tasks has been marked as done.
     * @param l1 This is the current list of Tasks.
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
        String Help = "\t 1. [Todo] - Adds a Task of type Todo.\n" +
                "\t    To add a Todo Task, type {todo} {task description}\n"+
                "\t 2. [Event] - Adds a Task of type Event.\n" +
                "\t    To add an Event Task, type {event} {task description} {/} {timing}\n" +
                "\t 3. [Deadline] - Adds a Task of type Deadline.\n" +
                "\t    To add a Deadline Task, type {event} {task description} {/} {timing}\n" +
                "\t 4. [Done] - Marks a specific Task as completed.\n" +
                "\t    To mark a Task as completed, type {done} {task index}\n" +
                "\t    To mark ALL Tasks as completed, type {done} {all}\n" +
                "\t 5. [Delete] - Delete Tasks from Task List.\n" +
                "\t    To delete Tasks from Task List, type {delete} {task index}\n" +
                "\t    To delete ALL Tasks from the Task List, type {delete} {all}\n" +
                "\t    ***Note: when using this command, Nini will prompt the User to\n" +
                "\t    confirm if they intend on deleting _ALL_ Tasks. User is then\n"+
                "\t    required to type Y for Yes or N for No.***\n" +
                "\t 6. [List] - Lists all currently available Tasks in the Task List.\n" +
                "\t    To retrieve the current list of Tasks, type {list}\n "+
                "\t 7. [Find] - Finds a list Tasks that matches the search key.\n"+
                "\t    To find a list of matching Tasks, type {find}";
        out.println("\t Below would be a list of commands that you will find useful!");
        out.println(Help);
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
    public void promptUser(String message){
        out.println(message);
    }
    public void close(){
        this.in.close();
    }
}




