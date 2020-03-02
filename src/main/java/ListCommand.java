/**
 * Extends from Command. Gives instructions on how to proceed when a list of the tasks is requested.
 */

public class ListCommand extends Command {

    /**
     * Stores the list of tasks in a text file on the hard disk.
     */

    private Storage storage;

    /**
     * Constructor for ListCommand.
     * @param command the command that the user typed in.
     */

    public ListCommand(String command) {
        super(command);
        storage = new Storage();
        for (Task task : Duke.tasks) {
            System.out.println(String.format("%d. %s", task.getTaskId(), task.toString()));
            storage.addToList(String.format("%d. %s", task.getTaskId(), task.toString()));
        }
        storage.storeList();
    }
}
