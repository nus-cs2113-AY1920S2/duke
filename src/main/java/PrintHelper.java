public class PrintHelper {

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
        System.out.println("____________________________________________________________");
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
        printWithIndentation("Hello! I'm Duke");
        printWithIndentation("What can I do for you?");
        printLine();
        System.out.println();
    }

    // Prints the bye (exit) message
    public static void printByeMessage(){
        printLine();
        printWithIndentation("Bye. Hope to see you again soon!");
        printLine();
    }

    // Prints a message to alert the user that an invalid task index was provided
    public static void printInvalidIndexAlert(){
        printLine();
        printWithIndentation("Invalid Command (done x : x should be a valid integer index)");
        printLine();
    }

    // Prints a message to alert the user that an index of the wrong format was provided
    public static void printInvalidIntegerAlert(){
        printLine();
        printWithIndentation("Invalid Command (done x : x should be an integer)");
        printLine();
    }

    // Prints a message to alert the user than an empty line was provided as input
    public static void printEmptyLineAlert() {
        printLine();
        printWithIndentation("You have entered a empty line, Please enter a valid command");
        printLine();
    }
}
