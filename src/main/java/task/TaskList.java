package task;
import ui.UI;
import exceptions.InvalidTaskException;
import java.util.ArrayList;

/**
 * Represents the main task list, with operations to list, add and delete tasks.
 * @see Task
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList () {
        this.tasks = new ArrayList<> (100);
    }

    /**
     * Prints all tasks in the task list.
     */
    public void listTasks() {
        UI.br();
        System.out.println("\t Dook will list your tasks now:");
        for (int i=0; i<tasks.size(); i++) {
            int taskNum = i+1;
            System.out.println("\t " + taskNum + ". " + tasks.get(i));
        }
        UI.br();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getListSize() {
        return tasks.size();
    }

    /**
     * Appends new task to the list.
     * @param t Task to be appended.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Sets a task's completion status to true and prints done message.
     * @param description String index of the task to be marked as done, based on its list position.
     */
    public void markDone(String description) {
        UI.br();
        int taskIdx = Integer.parseInt(description) -1; // -1 for zero-based indexing
        tasks.get(taskIdx).setDone();
        System.out.println("\t Dun dun dun dun! This task is done:");
        System.out.println("\t   " + tasks.get(taskIdx));
        UI.br();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param description String index of the task to be deleted, based on its list position.
     * @throws InvalidTaskException If task index is out of bounds.
     */
    public void deleteTask(String description) throws InvalidTaskException {
        int taskIdx = Integer.parseInt(description) -1;
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            throw new InvalidTaskException();
        }
        UI.br();
        System.out.println("\t This task has been whisked out of existence:");
        System.out.println("\t  " + tasks.get(taskIdx));
        tasks.remove(taskIdx);
        System.out.println("\t " + tasks.size() + " task(s) remaining.");
        UI.br();
    }
}
