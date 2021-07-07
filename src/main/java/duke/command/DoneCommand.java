package src.main.java.duke.command;

import src.main.java.Storage;
import src.main.java.TaskList;
import src.main.java.Ui;
import src.main.java.duke.exceptions.AlreadyDoneException;
import src.main.java.duke.exceptions.InvalidDoneException;
import src.main.java.duke.task.Task;

import java.io.IOException;

/**Mark specified task from Task List as done and display completed task to user
 *
 * @param taskNumber  ArrayList index of specified task
 * @throw InvalidDoneException  if taskNumber == 0 or taskNumber >= Total Number of Task in Task List
 * @throw AlreadyDoneException  if specified task isDone == true before command is executed
 */
public class DoneCommand extends Command{

    public static final String COMMAND_WORD = "done";

    private int taskNumber;

    /*Convert task number from number input to array index */
    public DoneCommand(String taskNumber){
        this.taskNumber = Integer.parseInt(taskNumber) - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDoneException, AlreadyDoneException, IOException {
        if (taskNumber == -1 || taskNumber >= Task.getTotalNumberOfTask()) {
            throw new InvalidDoneException();
        }
        if (tasks.getTaskFromList(taskNumber).getIsDone()) {
            throw new AlreadyDoneException();
        } else {
            tasks.completeTask(taskNumber);
            Ui.printCompletedMessage(tasks.getTaskFromList(taskNumber));
            storage.writeToFile(tasks);
        }
    }
}
