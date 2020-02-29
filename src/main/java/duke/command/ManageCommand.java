package duke.command;

import java.time.LocalDate;

import duke.TaskList;
import duke.excpetions.DukeException;
import duke.task.Task;

/**
 * A subtype of command which is used to manage the tasks already in the task list.
 */
public class ManageCommand extends Command{

    private int index;
    private LocalDate date;

    /**
     * A constructor that creates a manage command with type and index.
     *
     * @param type The type of a management command including : done ,list and show.
     * @param index The index of a "list" management command should be null.
     *              And for "done" command, it refers to the index number of the task to be done.
     * @param date Refers to the date that the users want to search for.
     */
    public ManageCommand(String type,int index,LocalDate date){
        super(type);
        this.index = index;
        this.date = date;
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
        case "show":
            tasks.showOneDayTasks(this);
            break;
        default:
            throw new DukeException();
        }
    }

    public int getIndex() {
        return index;
    }

    public LocalDate getDate() {
        return date;
    }
}
