package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.exceptions.InvalidDeleteException;
import src.main.java.duke.task.Task;

import java.io.IOException;

/**Remove specified task from Task List and display removed task to user
 *
 * @param taskNumber  ArrayList index of specified task
 * @throw InvalidDeleteException  if taskNumber == 0 or taskNumber >= Total Number of Task in Task List
 */
public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";

    private int taskNumber;

    /*Convert task number from number input to array index */
    public DeleteCommand(String taskNumber){
        this.taskNumber = Integer.parseInt(taskNumber) - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDeleteException, IOException {
        if (taskNumber == -1 || taskNumber >= Task.getTotalNumberOfTask()) {
            throw new InvalidDeleteException();
        } else {
            Task deletedTask = tasks.getTaskFromList(taskNumber);
            tasks.removeTaskFromList(taskNumber);
            Task.reduceTotalNumberOfTask();
            ui.printDeletedMessage(deletedTask);
            storage.writeToFile(tasks);
        }
    }
}
