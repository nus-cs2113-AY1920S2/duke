/**
 * Represents a parser for input by user
 * A Parser object corresponds to the String input by the user and extracts the command that the user
 * want to run
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
     * @return boolean
     */
    public boolean isBye() {
        return firstWord.equals("bye");
    }

    /**
     * Checks if command is 'list'
     * @return boolean
     */
    public boolean isList() {
        return firstWord.equals("list");
    }

    /**
     * Checks if command is 'done'
     * @return boolean
     */
    public boolean isDone() {
        return firstWord.equals("done");
    }

    /**
     * Checks if command is 'todo'
     * @return boolean
     */
    public boolean isToDo() {
        return firstWord.equals("todo");
    }

    /**
     * Checks if command is 'event'
     * @return boolean
     */
    public boolean isEvent() {
        return firstWord.equals("event");
    }

    /**
     * Checks if command is 'deadline'
     * @return boolean
     */
    public boolean isDeadline() {
        return firstWord.equals("deadline");
    }

    /**
     * Checks if command is 'delete'
     * @return boolean
     */
    public boolean isDelete() {
        return firstWord.equals("delete");
    }

    /**
     * Checks if command is 'help'
     * @return boolean
     */
    public boolean isHelp() {
        return firstWord.equals("help");
    }

    /**
     * Checks if command is 'find'
     * @return boolean
     */
    public boolean isFind() {
        return firstWord.equals("find");
    }
}