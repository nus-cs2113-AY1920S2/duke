public class PrintHelper {

    public static final String INVALID_COMMAND_MESSAGE = "Invalid command, Please type a valid command";
    public static final String INVALID_EVENT_FORMAT_MESSAGE = "Invalid format of declaration for event (event .......  /at ......)";
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Invalid format of declaration for deadline (deadline .......  /by ......)";
    public static final String DASHED_LINE = "__________________________________________________________________________________________";
    public static final String WELCOME_MESSAGE_LINE_1 = "Hello! I'm Duke";
    public static final String WELCOME_MESSAGE_LINE_2 = "What can I do for you?";
    public static final String EMPTY_LINE_ALERT_MESSAGE = "You have entered a empty line, Please enter a valid command";
    public static final String ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE = "Invalid Command (done x : x should be an integer)";
    public static final String INVALID_ARRAY_INDEX_MESSAGE = "Invalid Command (done x : x should be a valid integer index)";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    // Prints the number of spaces requested by the user
    public static void printSpaces(int numberOfSpaces){
        while (numberOfSpaces > 0) {
            System.out.print(" ");
            numberOfSpaces--;
        }
    }

    // Prints a line made up of '_'
    public static void printLine(){
        printSpaces(4);
        System.out.println(DASHED_LINE);
    }

    // Prints the line given by the user along with an indentation of 5 blank spaces
    public static void printWithIndentation(String line){
        printSpaces(5);
        System.out.println(line);
    }

    // Prints the line given by the user along with an indentation of certain number
    // of blank spaces provided by the user
    public static void printWithIndentation(String line, int numberOfSpaces){
        printSpaces(numberOfSpaces);
        System.out.println(line);
    }

    // Prints the welcome message
    public static void printWelcomeMessage(){
        printLine();
        printWithIndentation(WELCOME_MESSAGE_LINE_1);
        printWithIndentation(WELCOME_MESSAGE_LINE_2);
        printLine();
        System.out.println();
    }

    // Prints the bye (exit) message
    public static void printByeMessage(){
        printLine();
        printWithIndentation(BYE_MESSAGE);
        printLine();
    }

    // Prints a message to alert the user that an invalid task index was provided
    public static void printInvalidIndexAlert(){
        printLine();
        printWithIndentation(INVALID_ARRAY_INDEX_MESSAGE);
        printLine();
    }

    // Prints a message to alert the user that an index of the wrong format was provided
    public static void printInvalidIntegerAlert(){
        printLine();
        printWithIndentation(ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE);
        printLine();
    }

    // Prints a message to alert the user than an empty line was provided as input
    public static void printEmptyLineAlert() {
        printLine();
        printWithIndentation(EMPTY_LINE_ALERT_MESSAGE);
        printLine();
    }

    // Prints a message to alert the user didn't follow the correct format to create a deadline
    public static void printInvalidDeadlineAlert() {
        printLine();
        printWithIndentation(INVALID_DEADLINE_FORMAT_MESSAGE);
        printLine();
    }

    // Prints a message to alert the user didn't follow the correct format to create a event
    public static void printInvalidEventAlert() {
        printLine();
        printWithIndentation(INVALID_EVENT_FORMAT_MESSAGE);
        printLine();
    }

    // Prints a message to alert the user that he entered an invalid command
    public static void printInvalidCommand() {
        printLine();
        printWithIndentation(INVALID_COMMAND_MESSAGE);
        printLine();
    }

}
