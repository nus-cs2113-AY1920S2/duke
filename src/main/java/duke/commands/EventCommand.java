package duke.commands;

import duke.exceptions.TimeMissingException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.IOException;

import static duke.utils.Constants.EVENT_COMMAND;
import static duke.utils.Constants.EVENT_MARKER;

public class EventCommand extends AddCommand {
    private String description;
    
    public EventCommand(String description) {
        this.description = description;
        this.command = EVENT_COMMAND;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException {
        String[] taskAt = description.split(EVENT_MARKER);

        if (taskAt.length != 2) {
            throw new TimeMissingException();
        }

        String taskDescription = taskAt[0].trim();
        String at = taskAt[1].trim();
        Task task = new Event(taskDescription, at);
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
