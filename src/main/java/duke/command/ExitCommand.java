package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public ExitCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
    }
}
