package duke.commands;

import duke.exceptions.MarkerMissingException;
import duke.exceptions.TimeMissingException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;
import duke.tasks.Deadline;
import duke.tasks.Task;

import java.io.IOException;
import java.time.LocalDate;

import static duke.utils.Constants.DEADLINE_MARKER;

public class DeadlineCommand extends AddCommand {
    private String description;

    /**
     * Defines the constructor.
     * Fills in the task content and specifies the marker.
     *
     * @param description Task content with date.
     */
    public DeadlineCommand(String description) {
        this.description = description;
        this.marker = DEADLINE_MARKER;
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
     * @throws MarkerMissingException If there is no marker in the user input. 
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException {
        if (!description.contains(DEADLINE_MARKER)) {
            throw new MarkerMissingException(DEADLINE_MARKER);
        }
        
        String[] taskBy = description.split(DEADLINE_MARKER);

        if (taskBy.length != 2) {
            throw new TimeMissingException();
        }

        String taskDescription = taskBy[0].trim();
        String by = taskBy[1].trim();

        LocalDate byDate = LocalDate.parse(by);
        
        Task task = new Deadline(taskDescription, byDate);
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
