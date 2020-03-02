package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * AddCommand class - A class to process the execution of adding a task to the list
 */
public class AddCommand extends Command {
    private String fullCommand;
    private String prefix;

    /**
     * Constructor for an AddCommand given its command and prefix
     *
     * @param fullCommand user's input
     * @param prefix the prefix that tells what to execute
     */
    public AddCommand(String fullCommand, String prefix) {
        this.fullCommand = fullCommand;
        this.prefix = prefix;
    }

    /** Override abstract method of Command Class.
     * execute an AddCommand in TaskList class
     *
     * @param tasks the user's TaskList.
     * @param ui the user interface to inform them
     * @param storage the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(storage,fullCommand,prefix);
    }
}
