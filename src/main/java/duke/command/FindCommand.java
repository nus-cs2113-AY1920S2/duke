package duke.command;

import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    public FindCommand(String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        String keyword = this.details;
        Ui.printFindList(tasks, keyword);
    }
}
