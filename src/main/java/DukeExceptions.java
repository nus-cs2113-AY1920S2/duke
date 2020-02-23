/**
 * Represents the error messages that should be printed for different errors
 */
public class DukeExceptions {

    protected String input;

    public DukeExceptions() {
        this.input = null;
    }

    /**
     * Prints error message if tasklist is empty
     */
    public void printListExceptions() {
        System.out.println(" [Warning: There are currently no tasks!]");
    }

    /**
     * Prints error message if user tries to mark a completed task as done
     */
    public void printDoneExceptions() {
        System.out.println(" [Warning: The task(s) has already been marked as done]");
    }

    /**
     * Prints error message for IndexOutOfBoundsException for done command
     * @param input String input by user
     */
    public void printIOBDoneExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Index entered is out of range]");
        System.out.println(" done: marks task to be completed");
        System.out.println(" Parameters: done [TASK NUMBER]");
        System.out.println(" Example: done 1");
    }

    /**
     * Prints error message for NumberFormatException for done command
     * @param input String input by user
     */
    public void printNFEDoneExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Index entered is not a numerical value]");
        System.out.println(" done: marks task to be completed");
        System.out.println(" Parameters: done [Specifier]");
        System.out.println(" Example: done 1");
    }

    /**
     * Prints error message if ToDo task is missing description
     * @param input String input by user
     */
    public void printToDoExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Missing description(s)]");
        System.out.println(" todo: Adds todo items");
        System.out.println(" Parameters: todo [description]");
        System.out.println(" Example: todo read book");
    }

    /**
     * Prints error message if Event task is missing description / location or time
     * @param errorMessage error message to be printed
     * @param input String input by user
     */
    public void printEventExceptions(String errorMessage, String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: " + errorMessage + "]");
        System.out.println(" event: Adds event items");
        System.out.println(" Parameters: event [description] /at [date/time]");
        System.out.println(" Example: event project meeting /at Mon 2-4pm");
        System.out.println(" Disclaimer: Description field can be left empty");
    }

    /**
     * Prints error message if deadline task is missing description / date or time
     * @param errorMessage error message to be printed
     * @param input String input by user
     */
    public void printDeadlineExceptions(String errorMessage, String input) {
        System.out.println(" Command entered: " + input);
        System.out.println(" [Error: " + errorMessage + "]");
        System.out.println(" event: Adds deadline");
        System.out.println(" Parameters: deadline [description] /by [date]");
        System.out.println(" Example: deadline return book /by Sunday");
        System.out.println(" Disclaimer: Description field can be left empty");
    }

    /**
     * Prints error message for IndexOutOfBoundsException for delete command
     * @param input String input by user
     */
    public void printIOBDeleteExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Index entered is out of range]");
        System.out.println(" delete: deletes task from stored list");
        System.out.println(" Parameters: delete [TASK NUMBER]");
        System.out.println(" Example: delete 1");
    }

    /**
     *Prints error message for NumberFormatException for delete command
     * @param input String input by user
     */
    public void printNFEDeleteExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Index entered is not a numerical value]");
        System.out.println(" delete: deletes task from stored list");
        System.out.println(" Parameters: delete [TASK NUMBER]");
        System.out.println(" Example: delete 1");
    }

    /**
     * Prints error message if query is missing for find operation
     * @param input String input by user
     */
    public void printFindExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Missing search query]");
        System.out.println(" find: Searches through the tasks for matching queries");
        System.out.println(" Parameters: find [query]");
        System.out.println(" Example: find books");
    }

    /**
     * Prints error message if Duke is given invalid command
     */
    public void printInvalidInput() {
        System.out.println(" [WARNING: INVALID INPUT]");
    }
}