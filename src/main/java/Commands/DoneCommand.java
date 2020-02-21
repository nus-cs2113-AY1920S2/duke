package Commands;


import Asset.IllegalDukeException;
import Asset.Storage;
import Asset.Ui;
import Tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DoneCommand extends Command  {
    private int index;
    public Task task;

    public DoneCommand(String[] fullCommand){
        super(fullCommand);
        this.index=parseInt(fullCommand[1])-1;
    }
    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) throws IllegalDukeException, FileNotFoundException {
        if (index >= l1.size() || index < 0) {
            throw new IllegalDukeException(OUT_OF_BOUND_INDEX);
        }
        Task task = l1.get(this.index);
        task.done();
        this.task=task;
        ui.printDone(task);
        storage.saveFile(l1);
    }
}
