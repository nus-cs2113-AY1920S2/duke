package duke;

import duke.exceptions.InvalidKeywordException;

public class Command {
    protected Keyword keyword;
    protected String[] tokens;
    public Command(String userInput) throws InvalidKeywordException {
        if (userInput.length() == 0) {
            throw new InvalidKeywordException("Empty line");
        }
        tokens = userInput.split(" ");
        keyword = getKeywordFromString(tokens[0]);
    }

    protected Keyword getKeywordFromString(String keyword) throws InvalidKeywordException {
        try {
            return Keyword.valueOf(keyword.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidKeywordException(keyword);
        }
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public String[] getTokens() {
        return tokens;
    }
}
