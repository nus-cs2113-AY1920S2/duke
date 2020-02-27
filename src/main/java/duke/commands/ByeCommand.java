package duke.commands;

import duke.asset.Storage;
import duke.asset.Ui;
import duke.tasks.Task;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * This is the sub class of the Command class in Duke.
 *
 * This class changes the default status to -1, terminating the run() method in Duke.
 *
 * This class prints the good bye message to User
 */
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
