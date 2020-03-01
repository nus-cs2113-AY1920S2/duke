package duke.commands;

import duke.TaskList;
import duke.Storage;

/**
 * Command to execute when Duke is terminated.
 */
public class ExitCommand extends ExecuteCommand {

    /**
     * Constructor to set toExit of Duke to true.
     */
    public ExitCommand() {
        this.toExit = true;
    }

    /**
     * Display message when Duke is terminated.
     * @param tasks TaskList class object that handles all ArrayList of Tasks commands.
     * @param storage Storage class object that loads and saves data.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
