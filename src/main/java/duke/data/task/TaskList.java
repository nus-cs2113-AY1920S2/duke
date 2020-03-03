package duke.data.task;

import duke.commands.FindCommand;

import java.util.ArrayList;

/**
 * Handles the list of tasks by providing methods to interface with the list.
 * Extension of ArrayList
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Stores all the tasks provided.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList Class.
     * It creates a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Instructs {@link FindCommand#execute(TaskList)} to list the tasks with the search keyword
     * if the correct format is used.
     *
     * @param taskList Current list of tasks..
     * @param keyword  The word used for search.
     */
    public boolean findKeyword(String keyword, TaskList taskList) {
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
