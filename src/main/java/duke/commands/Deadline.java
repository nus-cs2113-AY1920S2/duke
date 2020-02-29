package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Deadline extends Command {
    public Deadline(String input) throws DukeException {
        super("[D][✗] "
                + input.replaceFirst("/by","(by:").trim() + ")");
        if (input.replaceFirst("/by(.*)","").matches("\\s*")){
            throw new DukeException("deadline",1);
        }
        if (!input.matches(".*/by\\s+\\w+.*")){
            throw new DukeException("deadline", 2);
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
