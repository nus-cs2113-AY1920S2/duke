/**
 * Extends from Command. Gives instructions on how to proceed when an Event task is added.
 */

public class EventCommand extends Command {

    /**
     * Creates a new Event task as requested by the user.
     */
    private Event event;

    /**
     * Constructor for EventCommand.
     * @param command the command that the user typed in.
     * @throws IndexOutOfBoundsException throws IndexOutOfBoundsException when there are invalid arguments.
     */

    public EventCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        event = new Event(command.substring(6));
        Duke.tasks.add(event);
        textUi.printEventMessage(event);
    }
}
