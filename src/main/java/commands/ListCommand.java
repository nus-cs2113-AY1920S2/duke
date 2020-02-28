package commands;

import java.util.ArrayList;
import tasks.Task;
import storage.Storage;

/**
 * Represents all the logic used in list command
 */
public class ListCommand extends TaskList {

    protected ArrayList<Task> tasks;
    protected Storage storage;

    public ListCommand() {
        tasks = new ArrayList<Task>();
        storage = new Storage();
    }

    /**
     * Prints out the list of  tasks in the task list
     */
    public void printList() {
        tasks = storage.loadTasks();
        if (tasks.size() == 0) {
            System.out.println(" [Warning: There are no items queued in the list]");
        } else {
            System.out.println(" Here are the list of tasks:");
            printTasks(tasks);
        }
    }
}
