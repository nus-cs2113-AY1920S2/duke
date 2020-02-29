package duke.commands;

import duke.exceptions.IllegalDeleteException;
import duke.util.TaskList;
import duke.util.UI;
import duke.exceptions.IllegalDoneTaskException;
import duke.taskmanager.Tasks;

import java.util.List;

public class DoneCommand extends Command {
    public UI ui;
    public static int taskNo;
    public static String task;
    public DoneCommand(UI ui) {
        taskNo = TaskList.getSize();
        this.ui = ui;
    }

    /**
     * Read and print the current task list, for the
     * user to select a task from that to be mark as done.
     * The list argument is the current task list.
     * @param list the current task list
     * @return task list after marking as done
     * @throws IllegalDoneTaskException when the task selected is
     *                                  not within the task list
     */
    public List<Tasks>  execute(List<Tasks> list) throws IllegalDoneTaskException {
        if (taskNo <= 0) {
            ListCommand.execute(list);
            return list;
        }
        ui.printDoneIntro();
        int indexOfTask = Integer.parseInt(ui.getStringInput());
        if (indexOfTask < taskNo && indexOfTask >= 0) {
            list.get(indexOfTask).markAsDone();
            ui.printRespondToDoneTask(indexOfTask, list.get(indexOfTask));
            return list;
        } else {
            throw new IllegalDoneTaskException();
        }
    }
}
