package duke.command;

import duke.TaskList;

/**
 *  A subtype of command which is used to delete a task from the task list.
 *  Every delete command contains an index specifying the task to be deleted.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * A constructor creates a new delete command with type and index.
     *
     * @param type Mark the category of the delete command as "delete".
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(String type,int index) {
        super(type);
        this.index=index;
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param tasks The task list where the execution will be done.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(this);
    }

    public int getIndex() {
        return index;
    }
}
