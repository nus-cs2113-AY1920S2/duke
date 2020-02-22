package Duke.Library;

import Duke.Exception.IllegalCommandException;
import Duke.Exception.IllegalDeleteException;
import Duke.Exception.IllegalDoneTaskException;
import Duke.Exception.IllegalTypeException;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Duke.Library.Dictionary.*;

public class Feature {

    public static FileWriter Duke_data;
    static File file = new File("data/output.txt");

    static {

        try {
            if (file.exists()) {
                Duke_data = new FileWriter(file, true);
            } else {
                Duke_data = new FileWriter(file);
            }
            Duke_data = new FileWriter("data/output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void echoUserCommand(String userCommand) {
        System.out.println("\t[Command entered: " + userCommand + "]");
    }

    public static void exitProgram() {
        displayExitMessage();
    }

    public static void displayTaskList() {
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

    public static void markTask() throws IllegalDoneTaskException {
        int index = sc.nextInt();
        index--;
        if (!TaskList.get(index).isDone()) {
            TaskList.get(index).markAsDone();
        } else {
            System.out.println("DONE ALREADY");
            throw new IllegalDoneTaskException();
        }
    }

    public static void deleteTask() throws IllegalDeleteException {
        int index = sc.nextInt();
        index--;
        if (index < TaskList.size()) {
            TaskList.remove(index);
        } else {
            throw new IllegalDeleteException();
        }

    }

    public static void addTask(String UserCommand) throws IllegalTypeException {
        String completeBy;
        switch (UserCommand) {
            case "EVENT":
                System.out.print("\n\tPlease Enter the Description: ");
                description = sc.nextLine();
                System.out.print("\n\tAt? ");
                completeBy = sc.nextLine();
                TaskList.add(new Event(description, completeBy));
                break;
            case "DEADLINE":
                System.out.print("\n\tPlease Enter the Description: ");
                description = sc.nextLine();
                System.out.print("\n\tTo be complete by? ");
                completeBy = sc.nextLine();
                TaskList.add(new Deadline(description, completeBy));
                break;
            case "TODO":
                System.out.print("\n\tPlease Enter the Description: ");
                description = sc.nextLine();
                TaskList.add(new Todo(description));
                break;
            default:
                throw new IllegalTypeException();
        }
        displayNumberOfTasks(TaskList.size());
        displayAcceptTask(description);
    }

    public static void clearTask() {
        TaskList.clear();
        displayNumberOfTasks(TaskList.size());
    }

    public static void executeCommand() {
        String exeCommand = getStringInput(sc);
        exeCommand = exeCommand.toUpperCase();
        try {
            if (!exeCommand.equals(COMMAND_EXIT_WORD)) {
                executeType(exeCommand);
            }
        } catch (IllegalCommandException e) {
            displayInvalidCommand();
            executeCommand();
        } catch (IllegalDeleteException e) {
            e.printStackTrace();
        } catch (IllegalTypeException e) {
            e.printStackTrace();
        } catch (IllegalDoneTaskException e) {
            e.printStackTrace();
        }
    }

    private static String getStringInput(Scanner userInput) {
        return userInput.nextLine();
    }

    private static Integer getIntegerInput(Scanner userInput) {
        return userInput.nextInt();
    }

    public static void executeType(String userCommand) throws IllegalCommandException, IllegalDeleteException, IllegalTypeException, IllegalDoneTaskException {
        try {
            switch (userCommand) {
                case COMMAND_REMOVE_WORD:
                case COMMAND_DELETE_WORD:
                    deleteTask();
                    break;
                case COMMAND_MARK_WORD:
                    markTask();
                    break;
                case COMMAND_CLEAR_WORD:
                    clearTask();
                    break;
                case COMMAND_DEADLINE_WORD:
                    addTask("DEADLINE");
                    break;
                case COMMAND_EVENT_WORD:
                    addTask("EVENT");
                    break;
                case COMMAND_LIST_WORD:
                    displayTaskList();
                    break;
                case COMMAND_TODO_WORD:
                    addTask("TODO");
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
            executeType(userCommand);
        } catch (IllegalDeleteException e) {
            System.out.println("    Sorry, task does not exist " +
                    "(IllegalDeleteException). ");
            executeType(userCommand);
        } catch (IllegalDoneTaskException e) {
            System.out.println("    Sorry, task does not exist " +
                    "(IllegalDoneTaskException). ");
            executeType(userCommand);
        }
        executeCommand();
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
        for (int i = 0; i < TaskList.size(); i++) {
            Duke_data.write(TaskList.get(i).toString() + "\n");
        }
        Duke_data.close();
    }

    public static void run() throws IOException {
        executeCommand();
        saveFile();
    }
}
