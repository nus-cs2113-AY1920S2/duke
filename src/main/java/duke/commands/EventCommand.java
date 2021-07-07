package duke.commands;

import duke.exceptions.MarkerMissingException;
import duke.exceptions.TimeMissingException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.IOException;
import java.time.LocalDate;

import static duke.utils.Constants.EVENT_MARKER;

public class EventCommand extends AddCommand {
    private String description;

    /**
     * Defines the constructor.
     * Fills in the task content and specifies the marker.
     *
     * @param description Task content with date.
     */
    public EventCommand(String description) {
        this.description = description;
        this.marker = EVENT_MARKER;
    }

    /**
     * Executes command "event".
     * Creates a new Event task with event date.
     * Adds the task into the existing task list.
     * Updates txt file whenever the task list changes.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException {
        if (!description.contains(EVENT_MARKER)) { // time marker is missing
            throw new MarkerMissingException(EVENT_MARKER);
        }

        String[] taskAt = description.split(EVENT_MARKER);

        if (taskAt.length != 2) {
            throw new TimeMissingException();
        }

        String taskDescription = taskAt[0].trim();
        String at = taskAt[1].trim();

        LocalDate atDate = LocalDate.parse(at);
        
        Task task = new Event(taskDescription, atDate);
        tasks.addTask(task);
        
        ui.displayAddTaskMessage(task, tasks.getListSize());

        // update the file
        try {
            storage.updateTasksToFile(tasks);
        } catch (IOException e) {
            ui.displayErrorMessage(e);
        }
    }
}
