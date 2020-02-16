public class Parser {

    protected String input;
    protected String firstWord;

    public Parser() {
        this.input = null;
        this.firstWord = null;
    }

    public String getFirstWord() {
        String removeTrailingSpaces = input.trim();
        String [] words = removeTrailingSpaces.split(" ");
        String firstWord = words[0].toLowerCase();
        return firstWord;
    }

    public void updateInput(String input) {
        this.input = input;
        this.firstWord = getFirstWord();
    }

    public boolean isBye() {
        return firstWord.equals("bye");
    }

    public boolean isList() {
        return firstWord.equals("list");
    }

    public boolean isDone() {
        return firstWord.equals("done");
    }

    public boolean isToDo() {
        return firstWord.equals("todo");
    }

    public boolean isEvent() {
        return firstWord.equals("event");
    }

    public boolean isDeadline() {
        return firstWord.equals("deadline");
    }

    public boolean isDelete() {
        return firstWord.equals("delete");
    }

}