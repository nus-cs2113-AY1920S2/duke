package duke;

import java.io.IOException;

/**
 * DeleteCommand class extended from Command class to deal with the
 * execution of deleting tasks
 */
public class DeleteCommand extends Command{
    private int index;

    /**
     * Constructor of given command given the task's index (started from 1)
     * @param index index of the task being deleted (started from 1)
     */
    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    /** Override abstract method of Command Class.
     * execute a DeleteCommand in TaskList class
     *
     * @param tasks the user's TaskList.
     * @param ui the user interface to inform them
     * @param storage the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.delete(index,storage);
    }
}
