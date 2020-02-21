package duke.commands;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Represents the result of a command execution
 */
public class CommandResult {

    /** Output displayed to user */
    public final String feedback;

    /** List of tasks to store results requested by user */
    public final ArrayList<Task> tasks;

    public CommandResult(String feedback) {
        this.feedback = feedback;
        tasks = null;
    }

    public CommandResult(String feedback,ArrayList<Task> tasks) {
        this.feedback = feedback;
        this.tasks = tasks;
    }

}
