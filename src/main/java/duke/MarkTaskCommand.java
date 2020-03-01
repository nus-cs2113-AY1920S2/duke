package duke;

import java.io.IOException;

public class MarkTaskCommand extends Command{
    private int index;

    /** Constructor for a Done Command.
     * @param index the index of the task that the user wants to mark done.
     */
    public MarkTaskCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    /** Override abstract method of Command Class.
     * execute a Done Command.
     * @param tasks     the user's TaskList.
     * @param storage   the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markAsDone(index, storage);
    }
}
