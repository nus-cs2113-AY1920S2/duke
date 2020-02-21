package Commands;


import Asset.Storage;
import Asset.Ui;
import Tasks.Task;
import java.util.ArrayList;

public class WrongCommand extends Command {


    public WrongCommand(String[] fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) {
        ui.printWrongInput();
    }
}
