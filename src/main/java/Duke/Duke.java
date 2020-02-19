package Duke;

import Duke.Exception.IllegalCommandException;
import Duke.Exception.IllegalDeleteException;
import Duke.Exception.IllegalDoneTaskException;
import Duke.Exception.IllegalTypeException;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Library of common words

    private static final int MAX_TASKS = 100;
    private static final String BOT_NAME = "E.D.I.T.H.";
    private static final String BOT_LOGO = "\n"
            + " _______         ______           _________        __________          __     __                \n"
            + "|   ____|       |    _  |         |__    __|      |___    ___|        |  |   |  |               \n"
            + "|   |___        |   | |  |           |  |             |  |            |  |___|  |               \n"
            + "|    ___|       |   | |  |           |  |             |  |            |   ___   |               \n"
            + "|   |___    _   |   |_|  |   _     __|  |__     _     |  |       _    |  |   |  |    _          \n"
            + "|_______|  |_|  |_______/   |_|   |________|   |_|    |__|      |_|   |__|   |__|   |_|         \n";

    private static final String USERNAME = "USER";
    private static final String MESSAGE_WELCOME = "\n\tHello! I'm " + BOT_NAME + "\n\tWhat can I do for you?";
    private static final String MESSAGE_EXIT = "\tBye. Hope to see you again soon!";
    private static final String LINE_DIVIDER = "\n\t____________________________________________________________";
    private static final String MESSAGE_INVALID_COMMAND = "\tInvalid Command. Please try again\n";
    private static final String MESSAGE_DESC_EMPTY = "\tInvalid Command! Description cannot be empty!";
    private static final String MESSAGE_BY_EMPTY = "\tInvalid Command! BY cannot be empty!";
    private static final String MESSAGE_AT_EMPTY = "\tInvalid Command! AT cannot be empty!";
    private static final String MESSAGE_MARK_EMPTY = "\tInvalid Command! Nothing is marked!";
    private static final String COMMAND_HELP_WORD = "HELP";
    private static final String COMMAND_HELP_DESC = "\n" +
            "\tHere's the help list\n" +
            "\t1. todo [Description]\n" +
            "\t\tadd in a todo Duke.task, format ' todo [Description] '\n" +
            "\t2. deadline\n" +
            "\t\tadd in a deadline Duke.task, format ' deadline [Description] /by[Time] ' \n" +
            "\t3. event\n" +
            "\t\tadd in an event Duke.task, format ' event [Description] /at[Time] '\n" +
            "\t4. list\n" +
            "\t\tlist out all the tasks\n" +
            "\t5. clear\n" +
            "\t\tclear all items from Duke.task\n" +
            "\t6. mark\n" +
            "\t\tmark a Duke.task by its index, format ' mark [index] '\n" +
            "\t7. bye\n" +
            "\t\texit the program\n";
    public static final String COMMAND_LIST_WORD = "LIST";
    public static final String COMMAND_CLEAR_WORD = "CLEAR";
    public static final String COMMAND_MARK_WORD = "MARK";
    public static final String COMMAND_DEADLINE_WORD = "DEADLINE";
    public static final String COMMAND_DELETE_WORD = "DELETE";
    public static final String COMMAND_EXIT_WORD = "EXIT";
    public static final String COMMAND_BYE_WORD = "BYE";
    public static final String COMMAND_EVENT_WORD = "EVENT";
    public static final String COMMAND_TODO_WORD = "TODO";

    public static final void displayWelcomeMessage() {
        System.out.println("\n" + LINE_DIVIDER + MESSAGE_WELCOME + LINE_DIVIDER + BOT_LOGO + LINE_DIVIDER);
    }

    public static final void displayAcceptTask(String userDescription) {
        System.out.println("\tGot it. I've added this " + userDescription);
    }

    public static final void displayInvalidCommand() {
        System.out.println(MESSAGE_INVALID_COMMAND);
    }

    public static final void displayInvalidDescription() {
        System.out.print(MESSAGE_DESC_EMPTY);
    }

    public static final void displayInvalidBy() {
        System.out.print(MESSAGE_BY_EMPTY);
    }

    public static final void displayInvalidAt() {
        System.out.print(MESSAGE_AT_EMPTY);
    }

    public static final void displayInvalidMark() {
        System.out.print(MESSAGE_MARK_EMPTY);
    }

    public static final void displayExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }

    public static final void displayHelpMenu() {
        System.out.print(COMMAND_HELP_DESC);
    }

    public static final void displayLineDivider() {
        System.out.println(LINE_DIVIDER);
    }

    public static final void displayNumberOfTasks(int Tasks) {
        System.out.print("\tNow you have " + Tasks + " tasks in the list.\n");
    }

    public static final void displayMarkedTask(int markedIndex) {
        System.out.print("\tYou have marked -- " + markedIndex);
    }

    private static void echoUserCommand(String userCommand) {
        System.out.println("\t[Command entered: " + userCommand + "]");
    }

    private static void exitProgram() {
        displayExitMessage();
    }

    private static void displayTaskList(ArrayList<Task> TaskList){
        if (!TaskList.isEmpty()) {
            for (int i = 0; i < TaskList.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(TaskList.get(i));
            }
            displayNumberOfTasks(TaskList.size());
        } else {
            System.out.println("\tEMPTY!!");
        }
    }

    private static void markTask(int index) throws IllegalDoneTaskException{

        if (!TaskList.get(index).isDone()){
            TaskList.get(index).markAsDone();
        } else {
            System.out.println("DONE ALREADY");
            throw new IllegalDoneTaskException();
        }


    }

    private static void deleteTask(int index) throws IllegalDeleteException{

        if (index < TaskList.size()){
            TaskList.remove(index);
        } else {
            throw new IllegalDeleteException();
        }

    }

    private static void addTask(String UserCommand) throws IllegalTypeException{
        if (UserCommand != ""){

        } else {
            throw new IllegalTypeException();
        }
    }


    public static ArrayList<Task> TaskList = new ArrayList(MAX_TASKS);
    private static boolean run = true;
    public static Scanner sc = new Scanner(System.in);
    private static String userCommand;
    private static String description;


    public static void executeCommand() {
        String exeCommand = sc.nextLine();
        try {
            while (!exeCommand.equals(COMMAND_EXIT_WORD)) {
                executeType(exeCommand); //to select the exeType and execute it
                exeCommand = sc.nextLine(); //get the next command
            }
        } catch (IllegalCommandException e) {
            displayInvalidCommand();
            executeCommand();
        }
    }

    public static void executeType(String exeCommand) throws IllegalCommandException {
        try {
            switch(userCommand.toUpperCase()){
                case COMMAND_DELETE_WORD:
                    deleteTask(sc.nextInt());
                    break;
                case COMMAND_MARK_WORD:
                    int index = sc.nextInt();
                    markTask(index);
                    break;
                case COMMAND_CLEAR_WORD:
                    addTask("H");
                    TaskList.clear();
                    break;
                case COMMAND_DEADLINE_WORD:
                    TaskList.add(new Deadline("HELLO WORLD", "0000"));
                    displayNumberOfTasks(TaskList.size());
                    displayAcceptTask("HELLO WORLD");
                    break;
                case COMMAND_EVENT_WORD:
                    TaskList.add(new Event("HELLO WORLD", "2359"));
                    displayNumberOfTasks(TaskList.size());
                    displayAcceptTask("HELLO WORLD");
                    break;
                case COMMAND_LIST_WORD:
                    displayTaskList(TaskList);
                    break;
                case COMMAND_TODO_WORD:
                    TaskList.add(new Todo("HELLO WORLD"));
                    displayNumberOfTasks(TaskList.size());
                    displayAcceptTask("HELLO WORLD");
                    break;
                case COMMAND_HELP_WORD:
                    displayHelpMenu();
                    break;
                default:
                    throw new IllegalCommandException();
            }
        } catch (IllegalTypeException e) {
            System.out.println("    Sorry,we do not understand your command. " +
                    "(IllegalTypeException). " +
                    "Please follow the instructions below.");
            executeType(exeCommand);
        } catch (IllegalDeleteException e) {
            System.out.println("    Sorry, task does not exist " +
                    "(IllegalDeleteException). ");
            executeType(exeCommand);
        } catch (IllegalDoneTaskException e) {
            System.out.println("    Sorry, task does not exist " +
                    "(IllegalDoneTaskException). ");
            executeType(exeCommand);
        }

    }

    private static FileWriter Duke_data;
    static {
        try {
            Duke_data = new FileWriter("data/output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Read stored input
    /*
    private static FileReader Duke_in;
    static {
        try{
            Duke_in = new FileReader("data/output.txt");
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void readFile() throws IOException {
        Duke_in.read();
        Duke_in.close();
    }*/

    public static void saveFile() throws IOException {
        for (int i = 0; i < TaskList.size(); i++){
            Duke_data.write(TaskList.get(i).toString()+"\n");
        }
        Duke_data.close();
    }

    public static void main(String[] args) throws IOException {
        displayWelcomeMessage();
        int index;
        while(run){
            userCommand = sc.nextLine();
            switch(userCommand.toUpperCase()){
                case COMMAND_DELETE_WORD:
                    index = sc.nextInt();
                    if (index - 1 < TaskList.size()){
                        TaskList.remove(index - 1);
                    } else {
                        System.out.println("Invalid Index");
                    }
                    break;
                case COMMAND_MARK_WORD:
                    index = sc.nextInt();
                    if (index - 1 < TaskList.size()){
                        TaskList.get(index - 1).markAsDone();
                    } else {
                        System.out.println("Invalid Index");
                    }
                    break;
                case COMMAND_CLEAR_WORD:
                    if (!TaskList.isEmpty()){
                        TaskList.clear();
                    } else {
                        System.out.println("It is already Empty!");
                    }
                    break;
                case COMMAND_DEADLINE_WORD:
                    System.out.print("\n\tPlease Enter the Description: ");
                    description = sc.nextLine();
                    System.out.print("\n\tTo be complete by? ");
                    String completeBy = sc.nextLine();
                    TaskList.add(new Deadline(description, completeBy));
                    displayNumberOfTasks(TaskList.size());
                    displayAcceptTask(description);
                    break;
                case COMMAND_EVENT_WORD:
                    System.out.print("\n\tPlease Enter the Description: ");
                    description = sc.nextLine();
                    System.out.print("\n\tAt? ");
                    completeBy = sc.nextLine();
                    TaskList.add(new Event(description, completeBy));
                    displayNumberOfTasks(TaskList.size());
                    displayAcceptTask(description);
                    break;
                case COMMAND_LIST_WORD:
                    displayTaskList(TaskList);
                    break;
                case COMMAND_TODO_WORD:
                    System.out.print("\n\tPlease Enter the Description: ");
                    description = sc.nextLine();
                    TaskList.add(new Todo(description));
                    displayNumberOfTasks(TaskList.size());
                    displayAcceptTask(description);
                    break;
                case COMMAND_HELP_WORD:
                    displayHelpMenu();
                    break;
                case COMMAND_EXIT_WORD:
                case COMMAND_BYE_WORD:
                    run = false;
                    break;
                default:
                    displayInvalidCommand();
                    break;
            }
            displayLineDivider();
        }
        saveFile();
    }
}