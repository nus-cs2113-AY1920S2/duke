package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    public ListCommand (String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        Ui.printList(tasks);
    }

}
