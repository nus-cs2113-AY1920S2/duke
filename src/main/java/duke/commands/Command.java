package duke.commands;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;

/**
 * Represents a command that the user can enter.
 */
public class Command {
    protected String parameters;

    /**
     * Constructor to create a new command.
     *
     * @param parameters the parameters of the command
     */
    public Command(String parameters) {
        this.parameters = parameters;
    }

    /**
     * Saves the list of tasks to a .txt file.
     *
     * @param tasks the list of tasks
     */
    public void execute(TaskList tasks) {
        try {
            Storage.saveToFile(tasks);
        } catch (IOException e) {
            System.out.println("Error saving!");
        }
    }
}
