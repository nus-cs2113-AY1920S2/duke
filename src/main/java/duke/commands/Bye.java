package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

public class Bye extends Command {

    /**
     * @param tasks     the tasks that will be augmented
     * @param ui        the messages that will be displayed
     * @param storage   the storage to be added into
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showBye();
    }


    /**
     * @return true, since this command would mean to exit the program.
     */
    @Override
    public boolean isExit(){
        return true;
    }

}
