package duke.commands;

import duke.asset.Storage;
import duke.asset.Ui;
import duke.tasks.Task;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * This is the sub class of the Command class in Duke.<br>
 * This class changes the default status to -1, terminating<br>
 * the run() method in Duke.<br>
 * This class prints the good bye message to User<br>
 */
public class ByeCommand extends Command {
    /**
     * This constructor creates a ByeCommand.<br>
     * @param fullCommand This is the input entered by user that has<br>
     *                    been split into an array of Strings.<br>
     */
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
