public class DukeExceptions {
    protected String input;

    public DukeExceptions() {
        this.input = null;
    }

    public void printListExceptions() {
        System.out.println(" [Warning: There are currently no tasks!]");
    }

    public void printDoneExceptions() {
        System.out.println(" [Warning: The task(s) has already been marked as done]");
    }

    public void printIOBDoneExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is out of range]");
        System.out.println(" done: marks task to be completed");
        System.out.println(" Parameters: done [TASK NUMBER]");
        System.out.println(" Example: done 1");
    }

    public void printNFEDoneExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is not a numerical value]");
        System.out.println(" done: marks task to be completed");
        System.out.println(" Parameters: done [Specifier]");
        System.out.println(" Example: done 1");
    }

    public void printToDoExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Missing specifier(s)]");
        System.out.println(" todo: Adds todo items");
        System.out.println(" Parameters: todo [description]");
        System.out.println(" Example: todo read book");
    }

    public void printEventExceptions(String errorMessage, String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: " + errorMessage + "]");
        System.out.println(" event: Adds event items");
        System.out.println(" Parameters: event [description] /at [date/time]");
        System.out.println(" Example: event project meeting /at Mon 2-4pm");
        System.out.println(" Disclaimer: Description field can be left empty");
    }

    public void printDeadlineExceptions(String errorMessage, String input) {
        System.out.println(" Command entered: " + input);
        System.out.println(" [Error: " + errorMessage + "]");
        System.out.println(" event: Adds deadline");
        System.out.println(" Parameters: deadline [description] /by [date]");
        System.out.println(" Example: deadline return book /by Sunday");
        System.out.println(" Disclaimer: Description field can be left empty");
    }

    public void printIOBDeleteExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is out of range]");
        System.out.println(" delete: deletes task from stored list");
        System.out.println(" Parameters: delete [TASK NUMBER]");
        System.out.println(" Example: delete 1");
    }

    public void printNFEDeleteExceptions(String input) {
        System.out.println(" Command Entered: " + input);
        System.out.println(" [Error: Specifier entered is not a numerical value]");
        System.out.println(" delete: deletes task from stored list");
        System.out.println(" Parameters: delete [TASK NUMBER]");
        System.out.println(" Example: delete 1");
    }

    public void printInvalidInput() {
        System.out.println(" [WARNING: INVALID INPUT]");
        System.out.println(" Here are the command list:");
        System.out.println("   1. todo: Creates a todo activity[Syntax: todo {description}]");
        System.out.println("   2. deadline: Creates a deadline activity[Syntax: deadline {description} /by"
                           + " {deadline}]");
        System.out.println("   3. event: Creates an event activity[Syntax: event {description} /at {time/date}]");
        System.out.println("   4. done: Mark event as done[Syntax: done {index of task}]");
        System.out.println("   5. delete: removes task from list[Syntax: delete {index of task}]");
        System.out.println("   6. list: displays list of all tasks[Syntax: list]");
        System.out.println("   7. save: save list of tasks[Syntax: save]");
    }
}