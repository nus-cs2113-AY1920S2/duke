package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

import static duke.constants.Messages.MESSAGE_NO_APPROPRIATE_KEYWORD;

public class FindCommand extends Command {
    public FindCommand(String command) {
        super();
        this.command = command;
    }

    public void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException {
        String[] words = command.split(" ", 2);
        if (words.length == 1) {
            throw new DukeException(MESSAGE_NO_APPROPRIATE_KEYWORD);
        }
        taskList.find(words[1].trim());
    }
    public boolean isExit() {
        return false;
    }
}
