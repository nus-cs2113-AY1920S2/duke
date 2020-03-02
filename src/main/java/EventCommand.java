public class EventCommand extends Command {
    private Event event;

    public EventCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        event = new Event(command.substring(6));
        textUi.printEventMessage(event);
        Duke.tasks.add(event);
    }
}
