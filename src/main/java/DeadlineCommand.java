public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        deadline = new Deadline(command.substring(9));
        textUi.printDeadlineMessage(deadline);
        Duke.tasks.add(deadline);
    }
}
