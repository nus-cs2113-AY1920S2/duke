package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

public abstract class Command {
    protected String command;

    /**
     * The method executes the command provided by the user
     * @param taskList The list of tasks provided by the user
     * @param uiInput The user interface
     * @param storage
     * @throws DukeException
     * @throws ParseException
     */
    public abstract void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException;

    public abstract boolean isExit();

}
