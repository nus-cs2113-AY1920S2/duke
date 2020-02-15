import task.*;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    // Libary of common words
    public static Scanner sc = new Scanner(System.in);
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
    private static final String MESSAGE_DELETE_EMPTY = "\tInvalid Command! Nothing is deleted!";
    private static final String COMMAND_ADD_WORD = "ADD";
    private static final String COMMAND_ADD_DESC = "Adds an item to the todo list";
    private static final String COMMAND_HELP_WORD = "HELP";
    private static final String COMMAND_HELP_DESC = "\n" +
            "\tHere's the help list\n" +
            "\t1. todo [Description]\n" +
            "\t\tadd in a todo task, format ' todo [Description] '\n" +
            "\t2. deadline\n" +
            "\t\tadd in a deadline task, format ' deadline [Description] /by[Time] ' \n" +
            "\t3. event\n" +
            "\t\tadd in an event task, format ' event [Description] /at[Time] '\n" +
            "\t4. list\n" +
            "\t\tlist out all the tasks\n" +
            "\t5. clear\n" +
            "\t\tclear all items from task\n" +
            "\t6. mark\n" +
            "\t\tmark a task by its index, format ' mark [index] '\n" +
            "\t7. bye\n" +
            "\t\texit the program";
    public static final String COMMAND_LIST_WORD = "LIST";
    public static final String COMMAND_MARK_WORD = "MARK";
    public static final String COMMAND_DEADLINE_WORD = "DEADLINE";
    public static final String COMMAND_EXIT_WORD = "EXIT";
    public static final String COMMAND_BYE_WORD = "BYE";
    public static final String COMMAND_EVENT_WORD = "EVENT";
    public static final String COMMAND_DONE_WORD = "DONE";
    public static final String COMMAND_TODO_WORD = "TODO";
    public static final String COMMAND_DELETE_WORD = "DELETE";

    public static final void displayWelcomeMessage() {System.out.println("\n" + LINE_DIVIDER + MESSAGE_WELCOME + LINE_DIVIDER + BOT_LOGO + LINE_DIVIDER); }
    public static final void displayAcceptTask() { System.out.println("\tGot it. I've added this task: "); }
    public static final void displayInvalidCommand() { System.out.println(MESSAGE_INVALID_COMMAND); }
    public static final void displayInvalidDescription() { System.out.print(MESSAGE_DESC_EMPTY);}
    public static final void displayInvalidBy() { System.out.print(MESSAGE_BY_EMPTY);}
    public static final void displayInvalidAt() { System.out.print(MESSAGE_AT_EMPTY);}
    public static final void displayInvalidMark() { System.out.print(MESSAGE_MARK_EMPTY);}
    public static final void displayInvalidDelete() { System.out.print(MESSAGE_DELETE_EMPTY);}
    public static final void displayExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }
    public static final void displayHelpMenu() {
        System.out.print(COMMAND_HELP_DESC);
    }
    public static final void displayLineDivider() {
        System.out.println(LINE_DIVIDER);
    }
    public static final void displayNumberOfTasks(int Tasks) { System.out.print("\tNow you have " + Tasks + " tasks in the list.");}
    public static final void displayMarkedTask(int markedIndex){ System.out.print("\tYou have marked -- " + markedIndex);}
    public static final void displayDeletedTask(int markedIndex){ System.out.print("\tYou have deleted -- " + markedIndex);}
    private static void echoUserCommand(String userCommand) {  System.out.println("\t[Command entered: " + userCommand + "]"); }
    private static void exitProgram() {
        displayExitMessage();
    }

    public static void main(String[] args) {
//        Task task[] = new Task[MAX_TASKS];
        ArrayList<Task> taskList = new ArrayList(MAX_TASKS);
        int index = 0;
        int markIndex;
        String input;
        displayWelcomeMessage();
        String userCommand, description = "", commandType;
        Task item;
        int dividerPosition;
        boolean run = true;
        while (run) {
            userCommand = sc.nextLine();
            displayLineDivider();
            echoUserCommand(userCommand);
            // Input Processing
            int startIndexOfInput = userCommand.indexOf(" ");
            int startIndexForDeadline = userCommand.indexOf("/by");
            int startIndexForEvent = userCommand.indexOf("/at");
            String completedBy;
            if (startIndexOfInput != -1) {
                commandType = userCommand.substring(0, startIndexOfInput).toUpperCase();
                if (startIndexForEvent != -1) {
                    completedBy = userCommand.substring(startIndexOfInput, startIndexForEvent);
                    description = userCommand.substring(startIndexOfInput + 1, startIndexForEvent);
                } else if (startIndexForDeadline != -1) {
                    completedBy = userCommand.substring(startIndexOfInput, startIndexForDeadline);
                    description = userCommand.substring(startIndexOfInput + 1, startIndexForDeadline);
                } else {
                    description = userCommand.substring(startIndexOfInput + 1);
                }
            } else {
                commandType = userCommand.toUpperCase();
                description = "";
            }

            // Command
            switch (commandType) {
                case COMMAND_TODO_WORD:
                    if (description == ""){
                        displayInvalidDescription();
                        break;
                    }
                    item = new Todo(description, index);
                    item.setType("[T]");
                    taskList.add(item);
                    index++;
                    displayAcceptTask();
                    displayNumberOfTasks(index);
                    break;
                case COMMAND_EVENT_WORD:
                    if (description == ""){
                        displayInvalidDescription();
                        break;
                    }
                    if (startIndexForEvent == -1 ){
                        displayInvalidAt();
                        break;
                    }
                    item = new Event(description, userCommand.substring(startIndexForEvent + 4), index);
                    item.setType("[E]");
                    taskList.add(item);
                    index++;
                    displayAcceptTask();
                    displayNumberOfTasks(index);
                    break;
                case COMMAND_DEADLINE_WORD:
                    if (description == "" ){
                        displayInvalidDescription();
                        break;
                    }
                    if (startIndexForDeadline == -1){
                        displayInvalidBy();
                        break;
                    }
                    item = new Deadline(description, userCommand.substring(startIndexForDeadline + 4), index);
                    item.setType("[D]");
                    taskList.add(item);
                    index++;
                    displayAcceptTask();
                    displayNumberOfTasks(index);
                    break;
                case COMMAND_DELETE_WORD:
                    if (description == "" ){
                        displayInvalidDelete();
                        break;
                    }
                    markIndex = Integer.parseInt(description);
                    taskList.remove(markIndex - 1);
                    displayDeletedTask(markIndex);
                    break;
                case COMMAND_MARK_WORD:
                    if (description == "" ){
                        displayInvalidMark();
                        break;
                    }
                    markIndex = Integer.parseInt(description);
                    taskList.get(markIndex - 1).setDone();
                    displayDeletedTask(markIndex);
                    break;
                case COMMAND_LIST_WORD:
                    if (index == 0) {
                        System.out.print("\tYour list is empty! Try adding to the list first :)");
                    } else {
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++){
                            System.out.print(taskList.get(i));
                        }
                    }
                    break;
                case "CLEAR":
                    if (index == 0) {
                        System.out.print("\tYour list is already empty!");
                    } else {
                        for (int i = 0; i < index; i++) {
                            taskList.remove(0);
                        }
                        index = 0;
                        System.out.print("\tYour list is cleared!");
                    }
                    break;
                case COMMAND_HELP_WORD:
                    displayHelpMenu();
                    break;
                case COMMAND_BYE_WORD:
                case COMMAND_EXIT_WORD:
                    run = false;
                    exitProgram();
                    break;
                default:
                    System.out.print("\tINVALID");
                    break;
            }
            displayLineDivider();
        }
    }
}