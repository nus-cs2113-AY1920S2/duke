package duke.command;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;

/**
 * Represents an executable command.
 */
public class Command {
    protected String details;

    public Command(String details) {
        this.details = details;
    }

    /**
     * Executes the command and returns the result.
     */
    public void executeCommand(TaskList tasks) {
        try {
            Storage.saveFile(tasks);
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }
}
