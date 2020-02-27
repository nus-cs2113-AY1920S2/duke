package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Deadline extends Command {

    /**
     * Constructor that specifies user input.
     * @param input the user input string
     * @throws DukeException If command is incomplete or empty
     */
    public Deadline(String input) throws DukeException {
        super("[D][✗] "
                + input.replaceFirst("/by","(by:").trim() + ")");
        if (input.replaceFirst("/by(.*)","").matches("\\s*")){
            throw new DukeException(1,"deadline");
        }
        if (!input.matches(".*/by\\s+\\w+.*")){
            throw new DukeException(2,"deadline");
        }
    }

    /**
     * @param tasks     the tasks that will be augmented
     * @param ui        the messages that will be displayed
     * @param storage   the storage to be added into
     * @throws DukeException Relays exceptions from methods
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            tasks.addTask(this);
            ui.showListIncrementOutput(command,tasks.list.size());
            storage.updateListDataOnDisk(tasks.list);
    }

    /**
     * @return false, since this is not a "bye" command.
     */
    @Override
    public boolean isExit(){
        return false;
    }

}
