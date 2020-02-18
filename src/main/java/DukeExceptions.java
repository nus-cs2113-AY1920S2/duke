/**
 * Represents the types of error messages Duke should display for different errors
 * DukeExceptions object contains error messages for Duke
 */
public class DukeExceptions {

    protected String input;

    public DukeExceptions() {
        this.input = null;
    }

    /**
     * Error message for Duke when the task list is Nan
     */
    public void printListExceptions() {
        System.out.println(" [Warning: There are currently no tasks!]");
    }

    /**
     * Error message for Duke when the task has been marked as completed but the user tries to mark it as
     * completed again
     */
    public void printDoneExceptions() {
        System.out.println(" [Warning: The task(s) has already been marked as done]");
    }

    /**
     * Error message for Duke when an IndexOutOfBoundsException occurs on done operation
     * @param input String input by user
     */
    public void printIOBDoneExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is out of range]");
        System.out.println(" done: marks task to be completed");
        System.out.println(" Parameters: done [TASK NUMBER]");
        System.out.println(" Example: done 1");
    }

    /**
     * Error messages for Duke when NumberFormatException occurs on done operation
     * @param input String input by user
     */
    public void printNFEDoneExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is not a numerical value]");
        System.out.println(" done: marks task to be completed");
        System.out.println(" Parameters: done [Specifier]");
        System.out.println(" Example: done 1");
    }

    /**
     * Error message for Duke when there is missing description(s) for todo operation
     * @param input String input by user
     */
    public void printToDoExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Missing specifier(s)]");
        System.out.println(" todo: Adds todo items");
        System.out.println(" Parameters: todo [description]");
        System.out.println(" Example: todo read book");
    }

    /**
     * Error message for Duke when there is a missing description(s) / date or time for event operation
     * @param errorMessage error message to be printed out
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
     * Error message for Duke when there is a missing description(s) / date or time for deadline operation
     * @param errorMessage error message to be printed out
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
     * Error message for Duke when there is an IndexOutOfBoundsException for delete operation
     * @param input String input by user
     */
    public void printIOBDeleteExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is out of range]");
        System.out.println(" delete: deletes task from stored list");
        System.out.println(" Parameters: delete [TASK NUMBER]");
        System.out.println(" Example: delete 1");
    }

    /**
     * Error message for Duke when there is a NumberFormatException for delete operation
     * @param input String input by user
     */
    public void printNFEDeleteExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is not a numerical value]");
        System.out.println(" delete: deletes task from stored list");
        System.out.println(" Parameters: delete [TASK NUMBER]");
        System.out.println(" Example: delete 1");
    }

    /**
     * Error message for Duke when keyword for find operation is missing
     * @param input String input by user
     */
    public void printFindExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is out of range]");
        System.out.println(" find: Searches through the tasks for matching queries");
        System.out.println(" Parameters: find [query]");
        System.out.println(" Example: find books");
    }

    /**
     * Error Message for Duke when command typed cannot be recognised
     */
    public void printInvalidInput() {
        System.out.println(" [WARNING: INVALID INPUT]");
    }

}