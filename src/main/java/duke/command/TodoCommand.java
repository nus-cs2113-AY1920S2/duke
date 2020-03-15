package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

/**
 * The TodoCommand class is the Object that adds Todo Object into the TaskList.
 * TodoCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class TodoCommand implements Command {
    private String description;

    /**
     * Public constructor for TodoCommand.
     * @param description Description of the Task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo function and add the Todo Task into the TaskList.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskCount = taskList.getTaskCount();
        taskList.addTask(new Todo(description));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.getTask(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }
}
