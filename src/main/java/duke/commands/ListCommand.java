package duke.commands;

import duke.common.Messages;
import duke.data.task.TaskList;
import duke.ui.TextUi;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        if (taskList.isEmpty()) {
            TextUi.printEmptyList();
        } else {
            TextUi.printList(taskList);
        }
    }
}
