package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Event extends Command {
    public Event(String input) throws DukeException {
        super("[E][✗] "
                + input.replaceFirst("/at","(at:").trim() + ")");
        if (input.replaceFirst("/at(.*)","").matches("\\s*")){
            throw new DukeException(1,"event");
        }
        if (!input.matches(".*/at\\s+\\w+.*")){
            throw new DukeException(2,"event");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this);
        ui.showListIncrementOutput(command,tasks.list.size());
        storage.updateListDataOnDisk(tasks.list);
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
