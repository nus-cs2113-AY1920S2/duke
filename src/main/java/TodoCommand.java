/**
 * Extends from Command. Gives instructions on how to proceed when a ToDo task is added.
 */

public class TodoCommand extends Command {

    /**
     * Creates a new ToDo task as requested by the user.
     */

    private ToDo todo;

    /**
     * Constructor for TodoCommand.
     * @param command the command that the user typed in.
     * @throws IndexOutOfBoundsException throws IndexOutOfBoundsException when there are invalid arguments.
     */

    public TodoCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        todo = new ToDo(command.substring(5));
        Duke.tasks.add(todo);
        textUi.printTodoMessage(todo);

    }
}
