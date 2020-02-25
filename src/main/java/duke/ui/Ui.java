package duke.ui;

import duke.task.TaskList;

public class Ui {

    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String INVALID_EVENT_FORMAT_MESSAGE = "Invalid format of declaration for event "
            + "(event .......  /at ......)";
    private static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Invalid format of declaration for deadline "
            + "(deadline .......  /by ......)";
    private static final String DASHED_LINE = "_________________________________________________________________"
            + "_________________________";
    private static final String WELCOME_MESSAGE_LINE_1 = "Hello! I'm Duke";
    private static final String WELCOME_MESSAGE_LINE_2 = "What can I do for you?";
    private static final String EMPTY_LINE_ALERT_MESSAGE = "You have entered a empty line, Please enter a valid"
            + " command";
    private static final String ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE_FOR_DONE_COMMAND = "Invalid Command "
            + "(done x : x should be a valid integer index)";
    private static final String ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE_FOR_DELETE_COMMAND = "Invalid Command "
            + "(delete x : x should be a valid integer index)";
    private static final String INVALID_ARRAY_INDEX_MESSAGE_FOR_DONE_COMMAND = "Invalid Command "
            + "(done x : x should be an integer)";
    private static final String INVALID_ARRAY_INDEX_MESSAGE_FOR_DELETE_COMMAND = "Invalid Command "
            + "(delete x : x should be an integer)";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String INVALID_TODO_FORMAT_MESSAGE = "OOPS!!! The description of a todo cannot be empty.";
    private static final String SINGLE_SPACE = " ";
    private static final String DONE_COMMAND = TaskList.DONE_COMMAND;
    private static final String DELETE_COMMAND = TaskList.DELETE_COMMAND;
    private static final String INVALID_FIND_FORMAT_MESSAGE = "Invalid Command "
            + "(find x : x should be a string)";


    // Prints the number of spaces requested by the user
    public static void printSpaces(int numberOfSpaces) {
        while (numberOfSpaces > 0) {
            System.out.print(SINGLE_SPACE);
            numberOfSpaces--;
        }
    }

    // Prints a line made up of '_'
    public static void printLine() {
        printSpaces(4);
        System.out.println(DASHED_LINE);
    }

    // Prints the line given by the user along with an indentation of 5 blank spaces
    public static void printWithIndentation(String line) {
        printSpaces(5);
        System.out.println(line);
    }

    // Prints the line given by the user along with an indentation of certain number
    // of blank spaces provided by the user
    public static void printWithIndentation(String line, int numberOfSpaces) {
        printSpaces(numberOfSpaces);
        System.out.println(line);
    }

    public static void printInvalidFindCommand() {
        printLine();
        printWithIndentation(INVALID_FIND_FORMAT_MESSAGE);
        printLine();
    }

    // Prints the welcome message
    public void printWelcomeMessage() {
        printLine();
        printWithIndentation(WELCOME_MESSAGE_LINE_1);
        printWithIndentation(WELCOME_MESSAGE_LINE_2);
        printLine();
        System.out.println();
    }

    // Prints the bye (exit) message
    public void printByeMessage() {
        printLine();
        printWithIndentation(BYE_MESSAGE);
        printLine();
    }

    // Prints a message to alert the user that an invalid task index was provided
    public static void printInvalidIndexAlert(String commandType) {
        printLine();
        switch (commandType) {
        case DONE_COMMAND:
            printWithIndentation(ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE_FOR_DONE_COMMAND);
            break;
        case DELETE_COMMAND:
            printWithIndentation(ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE_FOR_DELETE_COMMAND);
            break;
        default:
            // Add exception handling
        }
        printLine();
    }

    // Prints a message to alert the user that an index of the wrong format was provided
    public static void printIndexNotIntegerAlert(String commandType) {
        printLine();
        switch (commandType) {
        case DONE_COMMAND:
            printWithIndentation(INVALID_ARRAY_INDEX_MESSAGE_FOR_DONE_COMMAND);
            break;
        case DELETE_COMMAND:
            printWithIndentation(INVALID_ARRAY_INDEX_MESSAGE_FOR_DELETE_COMMAND);
            break;
        default:
            // Add exception handling
        }
        printLine();
    }

    // Prints a message to alert the user that an an empty line was provided as input
    public static void printEmptyLineAlert() {
        printLine();
        printWithIndentation(EMPTY_LINE_ALERT_MESSAGE);
        printLine();
    }

    // Prints a message to alert that the user didn't follow the correct format to create a deadline task
    public static void printInvalidDeadlineFormat() {
        printLine();
        printWithIndentation(INVALID_DEADLINE_FORMAT_MESSAGE);
        printLine();
    }

    // Prints a message to alert that the user didn't follow the correct format to create an event task
    public static void printInvalidEventFormat() {
        printLine();
        printWithIndentation(INVALID_EVENT_FORMAT_MESSAGE);
        printLine();
    }

    // Prints a message to alert that the user that he entered an invalid command
    public static void printInvalidCommand() {
        printLine();
        printWithIndentation(INVALID_COMMAND_MESSAGE);
        printLine();
    }

    // Prints a message to alert that the user didn't follow the correct format to create a todo task
    public static void printInvalidToDoFormat() {
        printLine();
        printWithIndentation(INVALID_TODO_FORMAT_MESSAGE);
        printLine();
    }

    // Prints a message to alert that the user didn't follow the correct format to mark a task done
    public static void printInvalidDoneFormat() {
        printIndexNotIntegerAlert(DONE_COMMAND);
    }


    public static void printInvalidDeleteFormat() {
        printIndexNotIntegerAlert(DELETE_COMMAND);
    }

    public void printEmptyLine() {
        System.out.println();
    }

}
