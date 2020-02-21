package Commands;

import Asset.Storage;
import Asset.Ui;
import Tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ByeCommand extends Command {
    public ByeCommand(String[] fullCommand){
        super(fullCommand );
        this.status=-1;
    }
    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) throws FileNotFoundException {
        ui.printGoodByeMessage();
        storage.saveFile(l1);
    }
}
