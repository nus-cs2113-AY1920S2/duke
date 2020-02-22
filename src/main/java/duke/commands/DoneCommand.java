package duke.commands;

import duke.Util.Tasklist;
import duke.Util.UI;
import duke.taskmanager.Tasks;

public class DoneCommand extends Command {
    public UI ui;
    public static int taskNo;
    public static String task;
    public DoneCommand(UI ui) {
        taskNo = Tasklist.getSize();
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.getDoneTask();
        int indexOfTask = getIndexOfTask();
        Tasks task = Tasklist.getTask(indexOfTask);
        Tasks tasks = new Tasks(task.getTask());
        ui.clearInput();
        if (indexOfTask < taskNo) {
            tasks.markAsDone();
            System.out.println("    Congrats! Task " + indexOfTask + ": " +
                    task.getTask() +  " has been completed!\n" +
                    "    Enter any key to continue.");
        } else {
            System.out.println("    Wrong task selected.");
        }
    }

    public int getIndexOfTask() {
        return Integer.parseInt(ui.getStringInput());
    }
}
