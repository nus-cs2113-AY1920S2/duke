package commands;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command class for the List command.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand () {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.listTasks(tasks);
    }
}
