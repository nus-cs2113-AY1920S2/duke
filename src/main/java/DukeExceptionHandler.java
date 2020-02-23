/**
 * Customer handler for errors
 */
public class DukeExceptionHandler {
    /**
     * Checks if the User supplied description is blank.
     * If it is blank, throws a BlankStringException.
     *
     * @param description The task description
     * @throws BlankStringException Throws exception for caller to catch and print error message
     */
    public static void isBlank(String description) throws BlankStringException {
        if (description.isBlank()) {
            throw new BlankStringException();
        }
    }

    /**
     * Throws a UnknownCommandException when User supplied in an unknown command
     *
     * @throws UnknownCommandException Throws exception for caller to catch and print error message.
     */
    public static void unknownCommand() throws UnknownCommandException {
        throw new UnknownCommandException();
    }
}
