package duke.commands;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingDescriptionException;
import duke.task.TaskList;

/**
 * Represents the command entered by the user, inherited by child classes which represent the specific commands.
 */
public class Command {
    protected String description;
    protected TaskList tasks;
    protected boolean isExit;

    /**
     * Constructor for Command class.
     * <p> <br>
     * Creates a new Command with the task description and task list.
     *</p>
     * @param description Description of the task provided by the user.
     * @param tasks Main task list containing all existing tasks.
     */
    public Command (String description, TaskList tasks) {
        this.description = description;
        this.tasks = tasks;
        this.isExit = false;
    }

    public void execute() throws MissingDescriptionException, InvalidCommandException {
        // To be implemented by child classes
    }

    public boolean getExitStatus() {
        return this.isExit;
    }
}
