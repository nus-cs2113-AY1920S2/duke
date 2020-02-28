package parser;

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
     * Updates command that user wants to run
     * @param input String input by user
     */
    public void updateInput(String input) {
        this.input = input;
        this.firstWord = getFirstWord();
    }

    /**
     * Returns true if command is bye, false otherwise
     * @return boolean value
     */
    public boolean isBye() {
        return firstWord.equals("bye");
    }

    /**
     * Returns true if command is list, false otherwise
     * @return boolean value
     */
    public boolean isList() {
        return firstWord.equals("list");
    }

    /**
     * Returns true if command is done, false otherwise
     * @return boolean value
     */
    public boolean isDone() {
        return firstWord.equals("done");
    }

    /**
     * Returns true if command is todo, false otherwise
     * @return boolean value
     */
    public boolean isToDo() {
        return firstWord.equals("todo");
    }

    /**
     * Returns true if command is event, false otherwise
     * @return boolean value
     */
    public boolean isEvent() {
        return firstWord.equals("event");
    }

    /**
     * Returns true if command is deadline, false otherwise
     * @return boolean value
     */
    public boolean isDeadline() {
        return firstWord.equals("deadline");
    }

    /**
     * Returns true if command is delete, false otherwise
     * @return boolean value
     */
    public boolean isDelete() {
        return firstWord.equals("delete");
    }

    /**
     * Returns true if command is help, false otherwise
     * @return boolean value
     */
    public boolean isHelp() {
        return firstWord.equals("help");
    }

    /**
     * Returns true if command is find, false otherwise
     * @return boolean value
     */
    public boolean isFind() {
        return firstWord.equals("find");
    }
}