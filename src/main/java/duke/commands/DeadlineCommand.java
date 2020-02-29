package duke.commands;

import duke.exceptions.TimeMissingException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;
import duke.tasks.Deadline;
import duke.tasks.Task;

import java.io.IOException;

import static duke.utils.Constants.DEADLINE_MARKER;

public class DeadlineCommand extends AddCommand {
    private String description;

    /**
     * Defines the constructor.
     * Fills in the task content.
     *
     * @param description Task content.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes command "deadline".
     * Creates a new Deadline task with deadline date.
     * Adds the task into the existing task list.
     * Updates txt file whenever the task list changes.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException {
        String[] taskBy = description.split(DEADLINE_MARKER);

        if (taskBy.length != 2) {
            throw new TimeMissingException();
        }

        String taskDescription = taskBy[0].trim();
        String by = taskBy[1].trim();
        Task task = new Deadline(taskDescription, by);
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
