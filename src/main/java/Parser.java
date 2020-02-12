public class Parser {
    protected String input;

    public Parser() {
        this.input = null;
    }

    public void updateInput(String input) {
        this.input = input;
    }

    String getInput() {
        return input;
    }

    public String getFirstWord() {
        String removeTrailingSpaces = input.trim();
        String [] words = removeTrailingSpaces.split(" ");
        String firstWord = words[0].toLowerCase();
        return firstWord;
    }

    public boolean isBye() {
        String firstWord = getFirstWord();
        return firstWord.equals("bye");
    }

    public boolean isList() {
        String firstWord = getFirstWord();
        return firstWord.equals("list");
    }

    public boolean isDone() {
        String firsWord = getFirstWord();
        return firsWord.equals("done");
    }

    public boolean isToDo() {
        String firstWord = getFirstWord();
        return firstWord.equals("todo");
    }

    public boolean isEvent() {
        String firstWord = getFirstWord();
        return firstWord.equals("event");
    }

    public boolean isDeadline() {
        String firstWord = getFirstWord();
        return firstWord.equals("deadline");
    }

    public boolean isDelete() {
        String firstWord = getFirstWord();
        return firstWord.equals("delete");
    }
}