package duke.commands;

import duke.Util.TaskList;
import duke.Util.UI;
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
