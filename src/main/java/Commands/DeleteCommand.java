package Commands;


import Asset.IllegalDukeException;
import Asset.Storage;
import Asset.Ui;
import Tasks.Task;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;
/**
 * This is a sub class of the Command class in Duke.
 *
 * This class deletes a Task from the ArrayList based on the task
 * number stated by the User.
 */
public class DeleteCommand extends Command  {
    private int index;

    public DeleteCommand(String[] fullCommand){
        super(fullCommand);
        this.index=parseInt(fullCommand[1])-1;
    }
    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) throws IllegalDukeException, FileNotFoundException {
        Task task=l1.get(this.index);
        if (index >= l1.size() || index < 0) {
            throw new IllegalDukeException(OUT_OF_BOUND_INDEX);
        }
        l1.remove(this.index);
        ui.printDelete(task, l1);
        storage.saveFile(l1);
        return;
    }

}
