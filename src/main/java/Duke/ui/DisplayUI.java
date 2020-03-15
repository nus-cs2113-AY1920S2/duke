package duke.ui;

import duke.taskManager.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayUI {

    private final Scanner in;

    private final PrintStream out;

    public DisplayUI() {
        this(System.in, System.out);
    }

    public DisplayUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static final String VERSION = "TASK MANAGER - Version 1.1";

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
                                             + "|   done       |                Mark a task as completed            | (eg. done 2)                          |\n"
                                             + "|   find       |                Find task with keyword              | (eg. find book)                       |\n"
                                             + "|   reset      |                reset program and Tasklist.txt      |                                       |\n"
                                             + "|   bye        |                Exit Planner                        |                                       |\n"
                                             + "|   help       |                Show this table                     |                                       |\n"
                                             + "|______________|____________________________________________________|_______________________________________|\n"
                                             + "LEGEND:   [O] ---- DONE | [X] ---- NOT DONE \n"
                                             + "Please key in your command: ";

    public static final String LINE_DIVIDER = "____________________________________________________________";

    public static final String INDENT = "    ";

    public static final String EMPTY_LINE = "\n";

    public static final String TASK_DELETED_MESSAGE = "____________________________________________________________\n"
                                                    + "\n    Noted. I've removed this task:";

    public static final String TASKNUMBER_LEFT_MESSAGE = "    Total number of tasks in your list: ";

    public static final String TASK_DONE_MESSAGE = "    Great job! I've marked this task as done in your planner: ";

    public static final String TASK_LEFT_MESSAGE = "    Here are your task(s) currently in your planner:";

    public static final String EMPTY_TASKS_MESSAGE = "    Your planner is empty currently! Try adding some tasks first!";

    public static  final String TASK_ADDED_MESSAGE = "    New task added:";

    public static  final String MATCHING_TASK_MESSAGE = "    Here are the matching tasks in your list:\n";

    public static  final String NO_MATCH_MESSAGE = "    Sorry! There are no task with descriptions matching your keyword! Please try again!\n";

    public static  final String BYE_MESSAGE = "    Bye! Hope to see you again soon!\n";

    /**
     * Print greeting message when program is launched
     */
    public void showStartMessages(){
        printToUser(
                DIVIDER,
                LOGO,
                VERSION,
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER,
                FUNCTION_LIST);
    }

    /**
     * Show the help functions table
     */
    public void showFunctionList(){
        printToUser(
                FUNCTION_LIST);
    }

    public void showDeletedTasks(ArrayList<Task> tasks, int tasksNumber){
        printToUser(
                TASK_DELETED_MESSAGE,
                INDENT + tasks.get(tasksNumber),
                TASKNUMBER_LEFT_MESSAGE + (tasks.size() - 1),
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    public void showDoneTask(ArrayList<Task> tasks, int taskNumber){
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                TASK_DONE_MESSAGE,
                INDENT + tasks.get(taskNumber),
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    public void showAllTask(){
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                TASK_LEFT_MESSAGE);
    }

    public void showAddedTask(Task addedTask, ArrayList<Task> tasks){
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                TASK_ADDED_MESSAGE + addedTask,
                TASKNUMBER_LEFT_MESSAGE + tasks.size(),
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    public void showEmptyTaskList(){
        printToUser(
                LINE_DIVIDER,
                EMPTY_LINE,
                EMPTY_TASKS_MESSAGE,
                EMPTY_LINE,
                LINE_DIVIDER);
    }

    /**
     * A printer to print the String of message
     * @param message String of message to be printed
     */
    public void printToUser(String... message){
        for(String m : message){
            out.println(m);
        }
    }
}


