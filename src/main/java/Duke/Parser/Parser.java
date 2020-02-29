package Duke.Parser;


public class Parser {

    public static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }

<<<<<<< HEAD
    public static String getWord(String userInput) throws DukeException {
        try {
            return userInput.strip().split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.INVALID_FORMAT);
        }
    }
=======
>>>>>>> branch-A-JavaDoc
}