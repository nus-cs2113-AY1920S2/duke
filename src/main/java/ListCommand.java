public class ListCommand extends Command {
    private Storage storage;

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
