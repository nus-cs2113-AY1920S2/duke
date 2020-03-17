package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <>Command</> class specifying the <>List</> command.
 */
public class List extends Command {

    public List() {
        super();
    }

    /**
     * @param tasks     the tasks that will be augmented
     * @param ui        the messages that will be displayed
     * @param storage   the storage to be added into
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showList(tasks.list);
    }


    /**
     * @return false, since this is not a "bye" command.
     */
    @Override
    public boolean isExit(){
        return false;
    }

}
