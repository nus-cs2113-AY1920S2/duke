package duke;

public class InvalidKeywordException extends Exception {
    public InvalidKeywordException(String badKeyword) {
        super("Invalid keyword: " + badKeyword);
    }
}
