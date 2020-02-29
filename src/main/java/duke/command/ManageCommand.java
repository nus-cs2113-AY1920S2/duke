package duke.command;

import duke.TaskList;
import duke.excpetions.DukeException;

/**
 * A subtype of command which is used to manage the tasks already in the task list.
 */
public class ManageCommand extends Command{

    private int index;

    /**
     * A constructor that creates a manage command with type and index.
     *
     * @param type The type of a management command including : done and list.
     * @param index The index of a "list" management command should be null.
     *              And for "done" command, it refers to the index number of the task to be done.
     */
    public ManageCommand(String type,int index){
        super(type);
        this.index = index;
    }

    /**
     * Execute a management command which do some operations on the current tasks.
     *
     * @param tasks The task list where the execution will be done.
     * @throws DukeException If something goes wrong during the execution.
     */
    @Override
    public void execute(TaskList tasks) throws DukeException{
        switch(type){
        case "done":
            tasks.doneTask(this);
            break;
        case "list":
            tasks.listTasks();
            break;
        default:
            throw new DukeException();
        }
    }

    public int getIndex() {
        return index;
    }
}
