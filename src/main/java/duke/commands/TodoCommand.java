package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Tasks;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

import static duke.constants.Messages.MESSAGE_TODO_DESCRIPTION_CANNOT_BE_EMPTY;

public class TodoCommand extends Command {
    public TodoCommand(String command) {
        super();
        this.command = command;
    }

    public void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException {
        String[] words = command.split(" ", 2);
        if (words.length == 1) {
            throw new DukeException(MESSAGE_TODO_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        else if (words[1].equals(" ") || (words[1].equals(""))) {
            throw new DukeException(MESSAGE_TODO_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        Tasks task = new Todo(words[1].trim());
        taskList.addTasks(task);
        storage.writeFile(taskList);
    }
    public boolean isExit() {
        return false;
    }
}
