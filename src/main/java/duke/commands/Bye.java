package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

public class Bye extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showBye();
    }


    @Override
    public boolean isExit(){
        return true;
    }

}
