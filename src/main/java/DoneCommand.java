/**
 * Extends from Command. Gives instructions on how to proceed when a task is completed.
 */

public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand.
     * @param command the command that the user typed in.
     */
    public DoneCommand(String command) {
        super(command);
        Duke.tasks.get(Integer.parseInt(Character.toString(
                command.charAt(command.length() - 1))) - 1).setDone();
    }
}
