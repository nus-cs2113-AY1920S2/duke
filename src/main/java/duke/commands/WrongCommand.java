package duke.commands;


import duke.asset.Storage;
import duke.asset.Ui;
import duke.tasks.Task;
import java.util.ArrayList;
/**
 * This is a sub class of the Command class in Duke.
 *
 * This class is meant for User input that does not
 * meet any of the list of commands available in Duke.
 */
public class WrongCommand extends Command {

    public WrongCommand(String[] fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) {
        ui.printWrongInput();
    }
}
