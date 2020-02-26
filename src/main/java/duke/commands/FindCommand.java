package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.ui.TextUi;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String description) {
        this.keyword = description;
    }

    @Override
    public void execute(TaskList tasklist) throws DukeException {
        boolean isFound = tasklist.findKeyword(this.keyword, tasklist);
        if (isFound) {
            TextUi.printTaskFound(keyword, tasklist);
        } else {
            TextUi.printKeyNotFound(keyword);
        }
    }
}
