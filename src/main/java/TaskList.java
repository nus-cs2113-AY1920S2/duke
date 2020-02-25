import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        this.printSize();
    }

    public Task getByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Prints all tasks which contain the specified keyword
     * @param name the specified keyword to search from the list
     */
    public void find(String name) {
        System.out.println("\tHere are the matching tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(name)) {
                System.out.printf("\t%d.%s%s", i, task, System.lineSeparator());
                i++;
            }
        }
        if (i == 1) {
            System.out.println("\tNo matching tasks found!");
        }
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
        if (this.size() == 0) {
            System.out.println("\tThere are no tasks in your list.");
            System.out.println("\tTip: Try adding a task by typing `todo NAME`");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
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