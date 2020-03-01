package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Event extends Command {

    /**
     * Constructor specifying user input.
     *
     * @param input the user input string
     * @throws DukeException If command is incomplete or empty
     */
    public Event(String input) throws DukeException {
        super("[E][ ] "
                + input.replaceFirst("\\s*/at\\s*"," (at: ").trim() + ")");
        if (input.replaceFirst("/at(.*)","").matches("\\s*")){
            throw new DukeException("event", 1);
        }
        if (!input.matches(".*/at\\s+\\w+.*")){
            throw new DukeException("event", 2);
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
