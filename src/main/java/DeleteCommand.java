public class DeleteCommand extends Command {
    public DeleteCommand(String command) throws IndexOutOfBoundsException {
        super(command);
        int selectedTaskId = Integer.parseInt(command.substring(7));
        Task task = Duke.tasks.get(selectedTaskId - 1);
        Duke.tasks.remove(task);
        textUi.printDeleteMessage(task, Duke.tasks.size());
    }
}
