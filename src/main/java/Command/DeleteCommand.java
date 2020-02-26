package Command;

import Exceptions.EmptyListException;
import Task.TaskList;

public class DeleteCommand extends Command {

    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute() {
        try{
            tasks.deleteTask(indexToDelete);
            storage.rebuildTaskFile(tasks.getTaskList());
        } catch (EmptyListException e) {
            System.out.println("[Error][Delete]: " + e);
        }
    }
}
