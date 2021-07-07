package duke.commands;

import duke.exceptions.ChatboxException;
import duke.exceptions.MarkerMissingException;
import duke.exceptions.TimeMissingException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static duke.utils.Constants.*;

/**
 * Command that checks all the tasks on a specific date.
 */
public class CheckCommand extends Command {
    private String description;
    private TaskList taskList;

    /**
     * Defines the constructor.
     * Starts the target list with empty list, specifies the target date, and specifies the marker.
     *
     * @param description Check marker with target date.
     */
    public CheckCommand(String description) {
        this.description = description;
        this.taskList = new TaskList();
        this.marker = CHECK_MARKER;
    }

    /**
     * Executes command "check".
     * Displays all tasks on a specific date.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException{
        if (!description.contains(CHECK_MARKER)) {
            throw new MarkerMissingException(CHECK_MARKER);
        }
        String[] taskOn = description.split(CHECK_MARKER);
        
        // "", "date"
        if (taskOn.length != 2) {
            throw new TimeMissingException();
        }
        
        String on = taskOn[1].trim();

        LocalDate date = LocalDate.parse(on);
        
        for (int i = 1; i <= tasks.getListSize(); i++) {
            Task task = tasks.getTask(i);
            
            if (task.getDate() == null) {
                continue;
            }
            
            if (task.getDate().equals(date)) {
                taskList.addTask(task);
            }
        }

        System.out.println(String.format("Here are the tasks on %s:", 
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.UK))));
        ui.listTasks(taskList);
    }
}
