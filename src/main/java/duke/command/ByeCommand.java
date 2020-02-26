package duke.command;

import duke.TaskList;
import duke.Storage;

/**
 * Command to terminate Duke program.
 */
public class ByeCommand extends Command {

    /**
     * Constructor which sets the exit condition of Duke to true.
     */
    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Prints out Duke's exit message.
     * @param tasks TaskList class object which handles operation involving ArrayList of Tasks
     * @param storage Storage class object which manages storing and loading of data
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Bob thanks you for coming! See you again soon!");
    }

}
