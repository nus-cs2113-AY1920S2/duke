package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ui.printFormat(tasks.findMatchingTasks(keyword));
    }
}
