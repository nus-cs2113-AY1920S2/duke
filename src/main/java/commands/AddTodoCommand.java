package commands;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class AddTodoCommand extends AddCommand {

    private String taskDescription;

    public static final String COMMAND_WORD = "todo";

    public AddTodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo todoTask = new Todo(this.taskDescription);
        tasks.addTask(todoTask);
        storage.saveTasks(tasks);
        ui.printNewTask(tasks);
    }
}
