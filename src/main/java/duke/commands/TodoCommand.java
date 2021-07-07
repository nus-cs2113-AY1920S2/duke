package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.IOException;

public class TodoCommand extends AddCommand {
    private String description;

    /**
     * Defines the constructor.
     * Fills in the task content.
     * 
     * @param description Task content.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes command "todo".
     * Creates a new Todo_task and adds the task into the existing task list.
     * Updates txt file whenever the task list changes.
     * 
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String taskDescription = description.trim();
        Task task = new Todo(taskDescription);
        tasks.addTask(task);
        
        ui.displayAddTaskMessage(task, tasks.getListSize());

        // update the txt file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
