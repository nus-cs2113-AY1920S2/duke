package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

import java.text.ParseException;
import static duke.constants.Messages.MESSAGE_DELETE_DESCRIPTION_CANNOT_BE_EMPTY;

public class DeleteCommand extends Command {
    public DeleteCommand (String command) {
        super();
        this.command = command;
    }

    public void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException {
        String[] words = command.split(" ", 2);
        if (words.length == 1) {
            throw new DukeException(MESSAGE_DELETE_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        else {
            int check = Integer.parseInt(words[1].trim()) - 1;
            taskList.deleteTask(check);
            storage.writeFile(taskList);
        }
    }
    public boolean isExit() {
        return false;
    }
}
