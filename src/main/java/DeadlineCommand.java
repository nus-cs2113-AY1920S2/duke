/**
 * Extends from Command. Gives instructions on how to proceed when a Deadline task is added.
 */

public class DeadlineCommand extends Command {

    /**
     * Create a new Deadline task as requested by the user.
     */

    private Deadline deadline;

    /**
     * Constructor for DeadlineCommand.
     * @param command the command that the user typed in.
     * @throws IndexOutOfBoundsException throws IndexOutOfBoundsException when there are invalid arguments.
     */
    public DeadlineCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        deadline = new Deadline(command.substring(9));
        textUi.printDeadlineMessage(deadline);
        Duke.tasks.add(deadline);
    }
}
