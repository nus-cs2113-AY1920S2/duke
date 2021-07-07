package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import static duke.constants.Messages.MESSAGE_NO_TASK_TO_MARK_DONE;

public class DoneCommand extends Command {
    public DoneCommand(String command) {
        super();
        this.command = command;
    }

    public void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException {
        String[] words = command.split(" ", 2);
        if (words.length == 1) {
            throw new DukeException(MESSAGE_NO_TASK_TO_MARK_DONE);
        }
        else {
            int check = Integer.parseInt(words[1].trim()) - 1;
            taskList.completeTask(check);
            storage.writeFile(taskList);
        }
    }
    public boolean isExit() {
        return false;
    }
}
