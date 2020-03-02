package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.task.Task;

/** Print out all task contained in Task List to user
*/
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public void execute(TaskList tasks, Ui ui, Storage storage){
        StringBuilder fullList = new StringBuilder();
        for (int i = 0; i < Task.getTotalNumberOfTask(); i++) {
            fullList.append(i + 1).append(".").append(tasks.getTaskFromList(i).toString()).append(System.lineSeparator());
        }
        ui.listTasks(fullList.toString().trim());
    }
}