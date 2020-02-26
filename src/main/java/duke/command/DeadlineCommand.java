package duke.command;

import duke.exception.MissingTaskException;
import duke.exception.MissingDeadlineDateException;
import duke.TaskList;
import duke.Storage;


/**
 * Command to add Deadline task into Duke.
 */
public class DeadlineCommand extends Command {

    private final String DEADLINE_COMMAND = "deadline";

    /**
     * Default constructor for Deadline Command
     * @param userInput String of full input given by user
     */
    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Processes the input and adds deadline task into TaskList object.
     * Saves existing TaskList object into file.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     * @throws MissingTaskException If deadline task description is not supplied
     * @throws MissingDeadlineDateException If deadline tasks due date is not supplied
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskException, MissingDeadlineDateException {
        if (!userInput.trim().equals(DEADLINE_COMMAND)) {
            int indexOfBy = userInput.indexOf("/by");
            if (indexOfBy == -1) {
                throw new MissingDeadlineDateException("Please specify a deadline using \'/by\'!");
            }
            String deadlineTask = userInput.substring(DEADLINE_COMMAND.length() + 1, indexOfBy - 1);
            String byDate = userInput.substring(indexOfBy + "/by".length() + 1);
            tasks.addDeadline(deadlineTask, byDate);
            storage.save(tasks);
        } else {
            throw new MissingTaskException("Deadline tasks cannot be empty!");
        }
    }


}
