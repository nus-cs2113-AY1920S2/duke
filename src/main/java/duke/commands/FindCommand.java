package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Command that finds all the tasks with a keyword.
 */
public class FindCommand extends Command {
    private String targetWord;
    private TaskList targetList;

    /**
     * Defines the constructor.
     * Starts the target list with empty list and specifies the keyword.
     * 
     * @param targetWord Word that user want to find.
     */
    public FindCommand(String targetWord) {
        this.targetWord = targetWord;
        this.targetList = new TaskList();
    }

    /**
     * Executes the command "find".
     * Displays all the tasks with a keyword.
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

        System.out.println(String.format("Here are the tasks matching \"%s\" in your list:", targetWord));
        ui.listTasks(targetList);
    }
}
