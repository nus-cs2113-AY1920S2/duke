package chatty.command;

import chatty.storage.Storage;
import chatty.task.TaskList;
import chatty.ui.Ui;

/**
 * Find command used in the application.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for find command.
     *
     * @param keyword The keyword in the find command.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets keyword in the find command.
     *
     * @return Keyword in the find command.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Executes the find command.
     *
     * @param taskList Task list containing all tasks.
     * @param ui       UI to handle sending message to users.
     * @param storage  Storage to handle reading and writing of tasks from disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = this.getKeyword();
        TaskList taskListWithKeyword = taskList.findTaskWithKeyword(keyword);
        ui.listTasksWithKeyword(taskListWithKeyword, keyword);
    }
}
