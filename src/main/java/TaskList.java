import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task getByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Prints all tasks which contain the specified keyword
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
    public void removeByTask(Task task) {
        tasks.remove(task);
    }

    public void removeByIndex(int index) {
        tasks.remove(index);
    }

    public void setDoneByIndex(int index) {
        tasks.get(index).setDone(true);
    }

    public void list() {
        for (int i = 0; i < this.size(); ++i) {
            System.out.printf("\t%d.%s", i+1, this.getByIndex(i));
            System.out.println();
        }
    }

    public int size() {
        return tasks.size();
    }

    public void printSize() {
        System.out.printf("\tNow you have %d tasks in the list.%s",
                this.size(), System.lineSeparator());
    }
}