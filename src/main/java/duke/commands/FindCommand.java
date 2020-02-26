package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.ui.TextUi;

public class FindCommand extends Command {
    String description;
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasklist) throws DukeException {
        boolean isFound = tasklist.find(this.description, tasklist);
        if (isFound) {
            TextUi.printTaskFound(description, tasklist);
        } else{
            TextUi.printTaskNotFound(description);
        }
    }
}
