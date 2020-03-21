package duke.parser;


import duke.exception.DukeException;
import duke.library.ErrorMessage;

/**
 * Parser to read String words.
 */
public class Parser {

    /**
     * Get Command word from userInput.
     *
     * @param userInput The userInput read by Ui.
     * @return The Command word.
     */

    public static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }

    /**
     * Get word from userInput.
     *
     * @param userInput The userInput read by Ui.
     * @return The word.
     * @throws DukeException If userInput is undefined.
     */

    public static String getWord(String userInput) throws DukeException {
        try {
            return userInput.strip().split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }

    /**
     * Get index from userInput.
     *
     * @param userInput The userInput read by Ui.
     * @return The index.
     * @throws DukeException If userInput is undefined.
     */

    public static int getIndex(String userInput) throws DukeException {
        try {
            int index = Integer.parseInt(userInput.replaceAll("\\D+", ""));
            return index - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }
}