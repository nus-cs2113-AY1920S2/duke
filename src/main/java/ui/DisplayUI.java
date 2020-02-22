package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class DisplayUI {

    private static final String DIVIDER = "=============================================================================================================";

    public static final String MESSAGE_WELCOME  = "Welcome to BAPE, your Task Manager!\n"
                                                + "Here are the functions available.";

    public static final String LOGO    = " _____     _      ______ ______\n"
                                       + "|  _  \\   / \\    |  __  |  ____|\n"
                                       + "| |_| |  / _ \\   | |__| | |___\n"
                                       + "|  _  | / /_\\ \\  |  ____|  ___|\n"
                                       + "| |_| |/ _____ \\ | |    | |____\n"
                                       + "|_____/_/     \\_\\|_|    |______|\n";

    public static final String FUNCTION_LIST =  " ___________________________________________________________________________________________________________\n"
                                             + "|  Functions:  |                 Descriptions:                      |               Example:                |\n"
                                             + "|______________|____________________________________________________|_______________________________________|\n"
                                             + "|   todo       |                Create a To-do task                 | (eg. todo borrow books)               |\n"
                                             + "|   deadline   |                Create a task with a deadline       | (eg. deadline bathe /by 9PM)          |\n"
                                             + "|   event      |                Create an event task                | (eg. event Meeting /at Library, 12PM) |\n"
                                             + "|   list       |                List all the task in your planner   |                                       |\n"
                                             + "|   done       |                Mark a task as completed            |                                       |\n"
                                             + "|   bye        |                Exit Planner                        |                                       |\n"
                                             + "|______________|____________________________________________________|_______________________________________|\n"
                                             + "Please key in your command: ";

    public void showStartMessages(String Version){
        printToUser(
                DIVIDER,
                LOGO,
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER,
                FUNCTION_LIST);
    }

    public void printToUser(String... message){
        for(String m : message){
            out.println(m);
        }
    }

    private final Scanner in;
    private final PrintStream out;

    public DisplayUI() {
        this(System.in, System.out);
    }

    public DisplayUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
}


