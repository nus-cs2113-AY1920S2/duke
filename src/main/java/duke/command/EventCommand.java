package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

public class EventCommand implements Command{
    private String taskDescription;
    private String atDate;

    public EventCommand(String taskDescription, String atDate) {
        this.taskDescription = taskDescription;
        this.atDate = atDate;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event eventTask = new Event(taskDescription, atDate);
        taskList.add(eventTask);
        ui.showAddTaskSuccessfulPrompt(taskList, eventTask);
    }
}
