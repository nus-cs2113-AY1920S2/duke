package duke;

import java.io.IOException;

/**
 * MarkTaskCommand class extended from Command class to deal with marking
 * tasks that are done by user
 */
public class MarkTaskCommand extends Command{
    private int index;

    /** Constructor for a DoneCommand with given index (started from 1)
     *
     * @param index the index of the task that the user wants to mark done
     *              (started from 1)
     */
    public MarkTaskCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    /** Override abstract method of Command Class.
     * execute a MarkTaskCommand in TaskList class
     *
     * @param tasks the user's TaskList.
     * @param ui the user interface to inform them
     * @param storage the storage to save any changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markAsDone(index, storage);
    }
}
