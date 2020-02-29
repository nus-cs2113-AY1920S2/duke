package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String targetWord;
    private TaskList targetList;

    /**
     * Defines the constructor.
     * Starts the target list with empty list.
     * 
     * @param targetWord Word that user want to find.
     */
    public FindCommand(String targetWord) {
        this.targetWord = targetWord;
        this.targetList = new TaskList();
    }

    /**
     * Executes the command "find".
     * Displays all the tasks on a certain date.
     *
     * @param tasks Task list that stores all the existing tasks.
     * @param ui Interaction with users.
     * @param storage Files related operation object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.getListSize(); i++) {
            Task task = tasks.getTask(i);
            
            if (task.getDescription().contains(targetWord)) {
                targetList.addTask(task);
            }
        }

        System.out.println("Here are the matching tasks in your list:");
        ui.listTasks(targetList);
    }
}
