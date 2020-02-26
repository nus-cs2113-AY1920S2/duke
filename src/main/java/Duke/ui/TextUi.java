package Duke.ui;

import Duke.data.TaskList;
import Duke.data.objects.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


//Messages
import static Duke.common.Messages.MESSAGE_TASKDONE;
import static Duke.common.Messages.MESSAGE_DELTASK;
import static Duke.common.Messages.MESSAGE_ADDTASK;
import static Duke.common.Messages.MESSAGE_GETLIST;
import static Duke.common.Messages.MESSAGE_FAREWELL;
import static Duke.common.Messages.MESSAGE_GREETING;
import static Duke.common.Messages.MESSAGE_HELP;
import static Duke.data.exceptions.DukeExceptions.printInvalidCommandException;


public class TextUi {
    private final Scanner in;
    private final PrintStream out;
    public static final String DIVIDER = "\t"+ "_".repeat(60);

    public TextUi() {
        this(System.in, System.out);
    }
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints out the String array given
     *
     * @param message - String... object
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }
    /**
     * Prints out MESSAGE_HELP message from Messages class
     *
     */
    public void showHelpMessage(){
        showToUser(
                MESSAGE_HELP
        );
    }
    /**
     * Prints out MESSAGE_GREETING message from Messages class
     *
     */
    public void showWelcomeMessage(String version) {
        showToUser(
                DIVIDER,
                version,
                MESSAGE_GREETING,
                DIVIDER);
    }
    /**
     * Prints out MESSAGE_FAREWELL message from Messages class
     *
     */
    public void showFarewellMessage(){
        showToUser(
                DIVIDER,
                MESSAGE_FAREWELL,
                DIVIDER);
    }
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }
    /**
     * Prints out MESSAGE_GETLIST message from Messages class & Loops through taskList
     * @param taskList - TaskList object
     */
    public void showTaskList(TaskList taskList){
        ArrayList<Task> tasks = taskList.getList();
        showToUser(
                DIVIDER,
                MESSAGE_GETLIST
        );
        for(int i = 0; i< tasks.size(); i++){
            System.out.println("\t"+(i+1)+"."+tasks.get(i).toString());
        }
        showToUser(
                DIVIDER
        );
    }
    /**
     * Prints out MESSAGE_ADDTASK message from Messages class & the Task size
     * @param taskList - TaskList object
     */
    public void showAddTask(TaskList taskList){
        ArrayList<Task> taskArrayList = taskList.getList();
        showToUser(
                DIVIDER,
                MESSAGE_ADDTASK,
                "\t\t"+taskArrayList.get(taskArrayList.size()-1).toString(),
                "\tNow you have "+ taskArrayList.size() +" tasks in the list."
        );
    }
    /**
     * Prints out MESSAGE_DELTASK message from Messages class & the Task size
     * @param taskList - TaskList object
     *                 taskNumber - the number(int) of the task being deleted
     */
    public void showDeleteTask(TaskList taskList, int taskNumber){
        ArrayList<Task> taskArrayList = taskList.getList();
        showToUser(
                DIVIDER,
                MESSAGE_DELTASK,
                "\t\t"+ taskArrayList.get(taskNumber),
                "\tNow you have "+ (taskArrayList.size()-1) +" tasks in the list.",
                DIVIDER
        );
    }
    /**
     * Prints out MESSAGE_TASKDONE message from Messages class & the Task that is marked Done
     * @param taskList - TaskList object
     *                 taskNumber - the number(int) of the task being marked as Done
     */
    public void showDoneTask(TaskList taskList, int taskNumber){
        ArrayList<Task> taskArrayList = taskList.getList();
        showToUser(
                DIVIDER,
                MESSAGE_TASKDONE,
                "\t\t"+taskArrayList.get(taskNumber).toString(),
                DIVIDER
        );
    }
    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty and pure whitespace.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();
        while (shouldIgnore(fullInputLine)){
            printInvalidCommandException();
            fullInputLine = in.nextLine();
        }
        String[] filteredArray = fullInputLine.split(" ", 2);
        String command = filteredArray[0];
        showToUser("[Command entered:" + command + "]");
        if(filteredArray.length == 2) {
            String attributes = filteredArray[1];
            showToUser("[Attributes entered:" + attributes + "]");
        }
        return fullInputLine;
    }
}
