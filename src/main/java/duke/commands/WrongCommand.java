package duke.commands;


import duke.asset.Storage;
import duke.asset.Ui;
import duke.tasks.Task;
import java.util.ArrayList;
/**
 * This is a sub class of the Command class in Duke.<br>
 * This class is meant for User input that does not<br>
 * meet any of the list of commands available in Duke.<br>
 */
public class WrongCommand extends Command {
    /**
     * This constructor creates a WrongCommand.<br>
     * @param fullCommand This is the input entered by user that has<br>
     *                    been split into an array of Strings.<br>
     */
    public WrongCommand(String[] fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) {
        ui.printWrongInput();
    }
}
