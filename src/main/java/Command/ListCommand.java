package Command;

public class ListCommand extends Command {

    @Override
    public void execute() {
        tasks.printList();
    }
}
