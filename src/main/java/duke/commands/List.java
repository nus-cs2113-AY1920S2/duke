package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

public class List extends Command {

    public List() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showList(tasks.list);
    }


    @Override
    public boolean isExit(){
        return false;
    }

}
