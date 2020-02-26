package commands;
import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;
import task.TaskList;

/**
 * Represents the command entered by the user.
 */
public class Command {
    protected String description;
    protected TaskList tasks;
    protected boolean isExit;

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
