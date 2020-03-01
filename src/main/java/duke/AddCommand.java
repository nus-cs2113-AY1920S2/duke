package duke;

import duke.task.Task;

import java.io.IOException;

public class AddCommand extends Command {
    private String fullCommand;
    private String prefix;

    /** Constructor for an AddCommand.
     */
    public AddCommand(String fullCommand, String prefix) {
        this.fullCommand = fullCommand;
        this.prefix = prefix;
    }

    /** Override abstract method of Command Class.
     * execute an AddCommand.
     * @param tasks     the user's TaskList.
     * @param storage   the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(storage,fullCommand,prefix);
    }
}
