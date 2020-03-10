/**
 * Extends from Command. Gives instructions on how to proceed when a task needs to be deleted.
 */

public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand.
     * @param command the command that the user typed in.
     * @throws IndexOutOfBoundsException throw IndexOutOfBoundsException when there are invalid arguments.
     */
    public DeleteCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        int selectedTaskId = Integer.parseInt(command.substring(7));
        Task task = Duke.tasks.get(selectedTaskId - 1);
        Duke.tasks.remove(task);
        textUi.printDeleteMessage(task, Duke.tasks.size());
    }
}
