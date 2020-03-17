package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract command class.
 */
public abstract class Command {
    public String command;

    public Command(String input) {
        this.command = input;
    }

    public Command() {}


    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();


}
