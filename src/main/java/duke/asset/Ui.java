package duke.asset;

import duke.tasks.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class handles with the interaction with User.<br>
 */
public class Ui  {
    public static final int MAX_LINE_LENGTH= 58;
    public static final String LINE = "\t__________________________________________________________";
    public static final String LIST_EMPTY= "\t Oops! No task has been assigned yet! Please enter a task!";
    public static final String MISSING_FILE = "Data file \"data.txt\" is missing!";
    public static final String MATCHING_TASK= "\t Here are the matching tasks in your list!";
    public static final String NO_MATCHING_TASK = "\t Oops! No such task can be found!";
    public static final String YES_OR_NO_ONLY = "\t I'm sorry but the options are only Y for YES or N for NO!";
    public static final String TASK_ADDED = "\t Got it. I've added this task:";
    public static final String AVAILABLE_TASKS = "\t Here are the tasks in your list:\n";
    public static final String GOODBYE_MESSAGE = "\t Bye.Hope to see you again soon!";
    public static final String REMOVE_ACKNOWLEDGEMENT = "\t Noted. I've removed this task: ";
    public static final String DONE_ACKNOWLEDGEMENT = "\t Nice! I've marked this task as done:";
    public static final String DONE_ALL_ACKNOWLEDGEMENT = "\t All tasks have been marked as done!";
    public static final String DELETE_ALL_ACKNOWLEDGEMENT = "\t All tasks have been deleted!";
    public static final String DELETE_ALL_CONFIRMATION = "\t Are you sure that you want ALL tasks to be deleted? " +
            "Type Y for Yes and N for No";
    public static final String IGNORE_DELETE_ALL = "\t List was not modified! Enter \"list\" to ensure all tasks are" +
            " still in the list!";
    public static final String DAB= "\t ``````````````````````````````````````````````````````````\n" +
            "\t ````````````````````````````````:v(v'`````````````````````\n" +
            "\t ```````````````````````````,)4|ex` `L}````````````````````\n" +
            "\t `````````````````````````!s\"'h `M:   ^z_;)T```````````````\n" +
            "\t `````````````````````````Z   u|}Z:        \"}``````````````\n" +
            "\t ``````````````````````:=?)                'B?L'```````````\n" +
            "\t `````````````````````'Z                    ;Yp\"```````````\n" +
            "\t `````````````````````.e                      ?_```````````\n" +
            "\t ```````````````````'QDGH                    .5````````````\n" +
            "\t ````````````````````I#g'         '_`        ||````````````\n" +
            "\t ```````````:mX_``````YhL`      `'q!`   ?     h````````````\n" +
            "\t ```````````'Qbs)|L=_V\" 'Y}     ^'q@&b=c=_`  ,Z````````````\n" +
            "\t ````````````q>     'T,   ,o\"    ;\"@c.   .sVx~`````````````\n" +
            "\t ````````````c!             \"bh` `,p       z:``````````````\n" +
            "\t `````````````q.              ngDQmh8s     X'``````````````\n" +
            "\t ``````````````LY\"             :n}>``b;   _V```````````````\n" +
            "\t ````````````````q?,                ?;   \"V````````````````\n" +
            "\t ``````````````KvR                  '  )x_`````````````````\n" +
            "\t ``````````````zjL                  GLY\"```````````````````\n" +
            "\t ```````````````M                 .I)``````````````````````\n" +
            "\t ```````````````Lz                `D:``````````````````````\n" +
            "\t ``````````````vu`    (I)_;\"\".      P.`````````````````````\n" +
            "\t ``````````````p     :X.':^,\"=n    \"q``````````````````````\n" +
            "\t `````````````)0X?^:)I````````m'   :j``````````````````````\n" +
            "\t `````````````.IGD8k'`````````<&G55M```````````````````````\n" +
            "\t ``````````````````````````````````````````````````````````";
    private final Scanner in;
    private final PrintStream out;

    /**
     * This constructor is called during the run process of Duke<br>
     * to create a IO class for Ui.<br>
     *
     */
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    /**
     * This methods gets the User input.<br>
     * @return the Scanner class to read User input.<br>
     */
    public String getUserIn(){
        return in.nextLine();
    }
    /**
     * This method prints the welcome message to User.<br>
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
     * This method prints constant LINE<br>
     */
    public void printLine(){
        out.println(LINE);
    }
    /**
     * This method also ensures that the message printed is within the standard<br>
     * length, which is the length of the constant LINE, 58 characters.
     * @param message is the String that we intend to format to a standard length<br>
     *                per line.<br>
     * @return String of standard length per line
     */
    public String formatMessage(String message) {
        StringTokenizer token = new StringTokenizer(message, " ");
        StringBuilder standardLengthMessage = new StringBuilder(message.length());
        int lineLength = 0;
        while (token.hasMoreTokens()) {
            String word = token.nextToken();
            if (lineLength + word.length() > MAX_LINE_LENGTH) {
                standardLengthMessage.append("\n\t ");
                lineLength = 0;
            }
            standardLengthMessage.append(word + " ");
            lineLength += word.length() + 1;
        }
        return standardLengthMessage.toString().stripTrailing();
    }
    /**
     * This method informs User that a new Task class has been created and added to list<br>
     * @param l1 This is the first argument of this method. It is the list of current<br>
     *           Tasks in Duke.<br>
     * @param task This is the second argument. This is the task that is to be printed to inform<br>
     *             user that the stated task has just been added.<br>
     */
    public void printAddTask(ArrayList<Task> l1, Task task){
        out.println(TASK_ADDED);
        out.println("\t  " + formatMessage(task.toString()));
        out.println("\t Now you have " + l1.size()+ " tasks in the list.");
    }

    /**
     * This method is to print the ArrayList that has been given to it.
     * @param l1 This is the ArrayList that needs to be printed.
     */

    public void printList(ArrayList<Task> l1){
        for (int i = 0; i < l1.size(); i++) {
            int count = i + 1;
            Task task = l1.get(i);
            out.println("\t " + formatMessage(count + "." + task.toString()));
        }
    }
    /**
     * This method shows User of all available Tasks.<br>
     * @param l1 is the current list of available Tasks.<br>
     * If l1 is empty, method will inform user that no Tasks<br>
     * are available.<br>
     */
    public void printCurrentList(ArrayList<Task> l1){
        if(l1.isEmpty()){
            out.println(LIST_EMPTY);
        }else {
            out.print(AVAILABLE_TASKS);
            printList(l1);
        }
    }
    /**
     * This method prints good bye message to User.<br>
     */
    public void printGoodByeMessage(){
        out.println(GOODBYE_MESSAGE);
        out.println(DAB);
    }
    /**
     * This method informs User that stated Task has been marked as done.<br>
     * @param task This is the only argument for this method. It is the<br>
     *             task that User wants to mark as done.<br>
     */
    public void printDone(Task task){
        out.println(DONE_ACKNOWLEDGEMENT);
        out.println(formatMessage("\t   "+task.toString()));
    }
    /**
     * This method informs User that stated Task has been deleted.<br>
     * @param task  This is the first argument of this method. It is<br>
     *              the task that the User wants to delete.<br>
     * @param l1    This is the second argument of this method. It is<br>
     *              the current list of Tasks.<br>
     */
    public void printDelete(Task task, ArrayList<Task> l1){
        out.println(REMOVE_ACKNOWLEDGEMENT);
        out.println("\t   " + formatMessage(task.toString()));
        out.println("\t Now you have " + l1.size() + " tasks in the list.");
    }
    /**
     * This method informs User that all Tasks has been marked as done.<br>
     * @param l1 This is the current list of Tasks.<br>
     */
    public void printDoneAll(ArrayList<Task> l1){
        out.println(DONE_ALL_ACKNOWLEDGEMENT);
        printList(l1);
    }
    /**
     * This method informs User that all Tasks has been deleted.<br>
     */
    public void printDeleteAll(){
        out.println(DELETE_ALL_ACKNOWLEDGEMENT);
    }
    /**
     * This method confirms with User if they want all Tasks to be deleted.<br>
     */
    public void confirmDeleteAll(){
        out.println(formatMessage(DELETE_ALL_CONFIRMATION));
    }
    /**
     * This method informs User that the delete ALL command is ignored.<br>
     */
    public void ignoreDeleteAll(){
        out.println(formatMessage(IGNORE_DELETE_ALL));
    }
    /**
     * This method informs User that command entered needs to be only Y for Yes or<br>
     * N for No.<br>
     */
    public void printYesOrNoOnly(){
        out.println(YES_OR_NO_ONLY);
    }
    /**
     * This method lists the supported commands for User.<br>
     */
    public void printHelp(){
        String Help = "\t 1. [Todo] - Adds a Task of type Todo.\n" +
                "\t    To add a Todo Task, type {todo} {task description}\n"+
                "\t 2. [Event] - Adds a Task of type Event.\n" +
                "\t    To add an Event Task, type {event} {task description}\n " +
                "\t    {/} {timing}\n" +
                "\t 3. [Deadline] - Adds a Task of type Deadline.\n" +
                "\t    To add a Deadline Task, type {event} {task description}\n" +
                "\t    {/} {timing}\n" +
                "\t 4. [Done] - Marks a specific Task as completed.\n" +
                "\t    To mark a Task as completed, type {done} {task index}\n" +
                "\t    To mark ALL Tasks as completed, type {done} {all}\n" +
                "\t 5. [Delete] - Delete Tasks from Task List.\n" +
                "\t    To delete Tasks from Task List, type {delete}\n " +
                "\t    {task index}\n" +
                "\t    To delete ALL Tasks from the Task List, type {delete}\n" +
                "\t    {all}\n" +
                "\t    ***Note: when using this command, Nini will prompt the\n" +
                "\t    User to confirm if they intend on deleting _ALL_ Tasks.\n" +
                "\t    User is then required to type Y for Yes or N for No.***\n" +
                "\t 6. [List] - Lists all currently available Tasks in the Task\n" +
                "\t    List.\n" +
                "\t    To retrieve the current list of Tasks, type {list}\n "+
                "\t 7. [Find] - Finds a list Tasks that matches the search key.\n"+
                "\t    To find a list of matching Tasks, type {find}";
        out.println("\t Below would be a list of commands that you will find\n" +
                "\t useful!");
        out.println(Help);
    }
    /**
     * This method informs User that data.txt is missing.<br>
     */
    public void showLoadingError(){
        out.println(MISSING_FILE);
    }
    /**
     * This method shows User the list of Tasks that matches with<br>
     * User search key.<br>
     * @param l1 is the list of all Tasks.<br>
     * @param l2 is the list of matching Tasks.<br>
     */
    public void printFindList(ArrayList<Task> l1, ArrayList<Integer> l2){
        if(l2.isEmpty()){
            out.println(NO_MATCHING_TASK);
        }else {
            out.println(MATCHING_TASK);
            for (int i = 0; i < l2.size(); i++) {
                int count = i + 1;
                Task task = l1.get(l2.get(i));
                out.println("\t " + formatMessage(count + "." + task.toString()));
            }
        }
    }
    /**
     * This method allows for other classes to print a message to the User.<br>
     * @param message This is the message that the other classes wish to prompt.<br>
     */
    public void promptUser(String message){
            out.println(formatMessage(message));
    }
/**
 * This method closes the System.in class of Duke.<br>
 */
    public void close(){
        this.in.close();
    }
}




