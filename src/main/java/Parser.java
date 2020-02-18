
/**
 * Represents a parser that stores the string input of user and extracts the command
 * Parser object corresponds to the String input by user and the first word of String input
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
     * @return String command to be run
     */
    public String getFirstWord() {
        String removeTrailingSpaces = input.trim();
        String [] words = removeTrailingSpaces.split(" ");
        String firstWord = words[0].toLowerCase();
        return firstWord;
    }

    /**
     * updates the string input command entered into the CLI
     * updates the first word of the input command
     * @param input String input command entered by the user
     */
    public void updateInput(String input) {
        this.input = input;
        this.firstWord = getFirstWord();
    }

    /**
     *checks if the current command to run is 'bye'
     */
    public boolean isBye() {
        return firstWord.equals("bye");
    }

    /**
     *Checks if the current command to run is 'list'
     */
    public boolean isList() {
        return firstWord.equals("list");
    }

    /**
     *Checks if the current command to run is 'done'
     */
    public boolean isDone() {
        return firstWord.equals("done");
    }

    /**
     *Checks if the current command to run is 'todo'
     */
    public boolean isToDo() {
        return firstWord.equals("todo");
    }

    /**
     *Checks if the current command entered is 'event'
     */
    public boolean isEvent() {
        return firstWord.equals("event");
    }

    /**
     *Checks if the current command entered is 'deadline'
     */
    public boolean isDeadline() {
        return firstWord.equals("deadline");
    }

    /**
     *Checks if the current command entered is 'delete'
     */
    public boolean isDelete() {
        return firstWord.equals("delete");
    }

    /**
     *Checks if the current command entered is 'help'
     */
    public boolean isHelp() {
        return firstWord.equals("help");
    }

    /**
     *Checks if the current command entered is 'find'
     */
    public boolean isFind() {
        return firstWord.equals("find");
    }

}