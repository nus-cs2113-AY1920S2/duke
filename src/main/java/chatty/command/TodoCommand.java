package chatty.command;

import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.task.ToDo;
import chatty.ui.Ui;

/**
 * Todo command used in the application.
 */
public class TodoCommand extends TaskCommand {

    /**
     * Constructor for todo command.
     *
     * @param description Description of the todo task in the command.
     */
    public TodoCommand(String description) {
        super(description);
    }

    /**
     * Executes the todo command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(this.getDescription());
        taskList.addTask(toDo);
        ui.sendTaskAddedMessage(toDo, taskList.getTotalTaskNum());
    }
}
