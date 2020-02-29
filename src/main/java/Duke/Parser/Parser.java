package Duke.Parser;


import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;

/**
 * Parser to read String words.
 */
public class Parser {

    public static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }

    public static String getWord(String userInput) throws DukeException {
        try {
            return userInput.strip().split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }

    public static int getIndex(String userInput) throws DukeException {
        try {
            int index = Integer.parseInt(userInput.replaceAll("\\D+", ""));
            return index - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }

}