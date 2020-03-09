package Command;

/**
 * Displays the list of tasks stored, both completed and uncompleted
 */
public class ListCommand extends Command {

    @Override
    public void execute() {
        tasks.printList();
    }
}
