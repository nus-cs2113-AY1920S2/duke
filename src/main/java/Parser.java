/**
 * Represents a parser for input by user
 * A Parser object corresponds to the String input by the user and extracts the command that the user wants to run
 */
public class Parser {

    protected String input;
    protected String firstWord;

    public Parser() {
        this.input = null;
        this.firstWord = null;
    }

    /**
     * Returns the command that the user wants to run
     * @return command
     */
    public String getFirstWord() {
        String removeTrailingSpaces = input.trim();
        String [] words = removeTrailingSpaces.split(" ");
        String firstWord = words[0].toLowerCase();
        return firstWord;
    }

    /**
     * Updates String input by user
     * @param input String input by user
     */
    public void updateInput(String input) {
        this.input = input;
        this.firstWord = getFirstWord();
    }

    /**
     * Checks if command is 'bye'
     * @return true if command is 'bye'; false otherwise
     */
    public boolean isBye() {
        return firstWord.equals("bye");
    }

    /**
     * Checks if command is 'list'
     * @return true if command is 'list'; false otherwise
     */
    public boolean isList() {
        return firstWord.equals("list");
    }

    /**
     * Checks if command is 'done'
     * @return true if command is 'done'; false otherwise
     */
    public boolean isDone() {
        return firstWord.equals("done");
    }

    /**
     * Checks if command is 'todo'
     * @return true if command is 'todo'; false otherwise
     */
    public boolean isToDo() {
        return firstWord.equals("todo");
    }

    /**
     * Checks if command is 'event'
     * @return true if command is 'event'; false otherwise
     */
    public boolean isEvent() {
        return firstWord.equals("event");
    }

    /**
     * Checks if command is 'deadline'
     * @return true if command is 'deadline'; false otherwise
     */
    public boolean isDeadline() {
        return firstWord.equals("deadline");
    }

    /**
     * Checks if command is 'delete'
     * @return true if command is 'delete' ; false otherwise
     */
    public boolean isDelete() {
        return firstWord.equals("delete");
    }

    /**
     * Checks if command is 'help'
     * @return true if command is 'help' ; false otherwise
     */
    public boolean isHelp() {
        return firstWord.equals("help");
    }

    /**
     * Checks if command is 'find'
     * @return true if command is 'find' ; false otherwise
     */
    public boolean isFind() {
        return firstWord.equals("find");
    }
}