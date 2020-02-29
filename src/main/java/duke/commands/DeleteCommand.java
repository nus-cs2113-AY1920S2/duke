package duke.commands;

import duke.util.TaskList;
import duke.util.UI;
import duke.exceptions.IllegalDeleteException;
import duke.taskmanager.Tasks;

import java.util.List;

public class DeleteCommand extends Command {
    public UI ui;
    public static int taskNo;
    public DeleteCommand (UI ui)  {
        this.ui = ui;
        taskNo = TaskList.getSize();
    }

    /**
     * Read and print the current task list, for the
     * user to select a task from that to be deleted.
     * The list argument is the current task list.
     * @param list the current task list
     * @return task list after deletion of task
     * @throws IllegalDeleteException when the task selected is
     *                                not within the task list
     */
    public List<Tasks> execute(List<Tasks> list) throws IllegalDeleteException {
        if (taskNo <= 0) {
            ListCommand.execute(list);
            return list;
        }
        ui.printDeleteIntro();
        int index = Integer.parseInt(ui.getStringInput());
        if (index < TaskList.getSize() && index >= 0) {
            Tasks task = TaskList.getTask(index);
            list.remove(index);
            ui.printTaskDeleted(index, task);
            return list;
        } else {
            throw new IllegalDeleteException();
        }
    }
}
