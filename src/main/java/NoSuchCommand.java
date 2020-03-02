/**
 * Extends from Command. Gives instructions on how to proceed when the command is not recognised.
 */

public class NoSuchCommand extends Command {

    /**
     * Constructor for NoSuchCommand.
     * @param command the command that the user typed in.
     * @throws IllegalArgumentException throws IllegalArgumentException within the constructor for the main method
     * to catch.
     */
    public NoSuchCommand(String command) throws IllegalArgumentException{
        super(command);
        throw new IllegalArgumentException();
    }
}
