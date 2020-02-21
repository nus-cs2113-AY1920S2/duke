package Asset;

import Tasks.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;





public class Ui  {public static final String LINE = "\t__________________________________________________________";
    public static final String[] COMMAND= {"todo", "deadline", "event", "done", "bye", "list", "help"};
    public static final String WRONG_INPUT="\t ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n" +
            "\t Input command is wrong. Enter \"help\" for list of accepted\n\t commands";
    public static final String LIST_EMPTY= "\t Oops! No task has been assigned yet! Please enter a task\n\t before" +
            " listing";
    public static final String MISSING_FILE = "Please check if data.txt exist!";
    private final Scanner in;
    private final PrintStream out;


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserIn(){
        return in.nextLine();
    }

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

    public void printError(String message){
        out.println(LINE);
        out.println(message);
        out.println(LINE);
    }
    public void printAddTask(ArrayList<Task> l1, Task task){
        out.println(LINE);
        out.println("\t Got it. I've added this task:");
        out.println("\t   "+task.toString());
        out.println("\t Now you have " + l1.size()+ " tasks in the list.");
        out.println(LINE);
    }

    public void printGoodByeMessage(){
        out.println(LINE);
        out.println("\t Bye.Hope to see you again soon!");
        out.println(LINE);
    }
    public void printDone(Task task){
        out.println(LINE);
        out.println("\t Nice! I've marked this task as done:");
        out.println("\t   "+task.toString());
        out.println(LINE);
    }
    public void printDelete(Task task, ArrayList<Task> l1){
        out.println(LINE);
        out.println("\t Noted. I've removed this task: ");
        out.println("\t   " + task.toString());
        out.println("\t Now you have " + l1.size() + " tasks in the list.");
        out.println(LINE);
    }
    public void printList(ArrayList<Task> l1){
        out.println(LINE);
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
        out.println(LINE);
    }
    public void printWrongInput(){
        out.println(LINE);
        out.println(WRONG_INPUT);
        out.println(LINE);
    }
    public void printHelp(){
        out.println(LINE);
        out.println("\t "+ Arrays.toString(COMMAND));
        out.println(LINE);
    }
    public void showLoadingError(){
        out.println(LINE);
        out.println(MISSING_FILE);
        out.println(LINE);
    }
}




