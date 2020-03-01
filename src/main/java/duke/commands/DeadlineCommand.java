package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DeadlineTaskException;
import duke.exceptions.DeadlineDateException;

/**
 * Command to add Deadline tasks.
 */
public class DeadlineCommand extends ExecuteCommand {

    /**
     * Constructor for Deadline Command.
     * @param userData String of user input.
     */
    public DeadlineCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    /**
     * Parse user input and adds deadline tasks into TaskList object.
     * Stores existing TaskList object into output file.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     * @throws DeadlineTaskException If description of deadline tasks is missing.
     * @throws DeadlineDateException If date of deadline tasks is missing.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DeadlineTaskException, DeadlineDateException {
        if (!userData.trim().toLowerCase().equals("deadline")) {
            int indexOfBy = userData.indexOf("/by");
            if (indexOfBy == -1) {
                throw new DeadlineDateException("Please specify a date by using \'/by\'!");
            }
            String[] newData = userData.split(" /by ");
            if (newData.length == 1) {
                throw new DeadlineDateException("Please specify a date after \'/by\'!");
            }
            tasks.deadlineTask(newData[0], newData[1]);
            storage.saveData(tasks);
        } else {
            throw new DeadlineTaskException("Add a task behind 'deadline', task cannot be left empty.");
        }
    }
}
