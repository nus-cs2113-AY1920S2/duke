package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.util.Storage;

public class TodoCommand implements Command{
    private String taskDescription;

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todoTask = new Todo(taskDescription);
        taskList.add(todoTask);
        ui.showAddTaskSuccessfulPrompt(taskList, todoTask );
    }
}
