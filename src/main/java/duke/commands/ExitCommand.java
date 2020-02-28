package duke.commands;

import duke.task.TaskList;

/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand (String description, TaskList tasks) {
        super(description, tasks);
    }

    /**
     * Sets the exit status of the program to true, so that it terminates on the next iteration.
     */
    @Override
    public void execute() {
        this.isExit = true;
    }
}