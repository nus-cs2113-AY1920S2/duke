import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Add a task to this list.
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified position in this list.
     * (Note: 1st item is at index 0)
     * @param index Index of the task to return
     * @return The task at the specified position in this list
     */
    public Task getByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Prints all tasks which contain the specified keyword.
     * @param name the specified keyword to search from the list
     * @return number of tasks found
     */
    public int find(String name) {
        int taskCount = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(name)) {
                taskCount++;
                System.out.printf("\t%d.%s%s", taskCount, task, 
                        System.lineSeparator());
            }
        }
        return taskCount;
    }

    /**
     * Removes the first occurrence of the specified task from this list,
     * if it is present.  If the list does not contain the task, it is
     * unchanged.
     * Returns {@code true} if this list contained the specified element
     * (or equivalently, if this list changed as a result of the call).
     * @param task task to be removed from this list, if present
     * @return {@code true} if this list contained the specified task
     */
    public boolean removeByTask(Task task) {
        return tasks.remove(task);
    }

    /**
     * Removes the task at the specified position in this list.
     * Shifts any subsequent tasks to the left (subtracts one from their indices).
     * Returns the task that was removed from the list.
     * @param index the index of the task to be removed
     * @return the task previously at the specified position
     */
    public Task removeByIndex(int index) {
        return tasks.remove(index);
    }

    /**
     * Sets the first occurrence of the specified task from the list as {@code \u2713},
     * if it is present. If the list does not contain the task, it is unchanged.
     * @param index task to be marked as {@code \u2713}
     */
    public void setDoneByIndex(int index) {
        this.getByIndex(index).setDone(true);
    }

    /**
     *  Lists all tasks in the list.
     */
    public void list() {
        for (int i = 0; i < this.size(); ++i) {
            System.out.printf("\t%d.%s", i + 1, this.getByIndex(i));
            System.out.println();
        }
    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Prints the number of tasks in the list with some words.
     */
    public void printSize() {
        System.out.printf("\tNow you have %d tasks in the list.%s",
                this.size(), System.lineSeparator());
    }
}