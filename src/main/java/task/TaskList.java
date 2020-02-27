package task;
import ui.UI;
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
     *
     * @param t Task to be appended.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }


    /**
     * Finds tasks with matching descriptions and lists them.
     *
     * @param description Text to search for.
     */
    public void findTasks(String description) {
        UI.br();
        System.out.println("\t Dook has found the following tasks: ");
        int i=1;
        for (Task t : tasks) {
            if (t.getDescription().contains(description)) {
                System.out.println("\t  " + i + ". " + t);
                i++;
            }
        }
        if (i==1) {
            System.out.println("\t  No matching tasks :(");
        }
        UI.br();
    }

    /**
     * Sets a task's completion status to true and prints done message.
     *
     * @param description String index of the task to be marked as done, based on its list position.
     */
    public void markDone(String description) {
        UI.br();
        try {
            int taskIdx = Integer.parseInt(description) - 1; // -1 for zero-based indexing
            tasks.get(taskIdx).setDone();
            System.out.println("\t Dun dun dun dun! This task is done:");
            System.out.println("\t   " + tasks.get(taskIdx));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Dook does not recognise this task!");
        }
        UI.br();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param description String index of the task to be deleted, based on its list position.
     */
    public void deleteTask(String description) {
        UI.br();
        try {
            int taskIdx = Integer.parseInt(description) - 1;
            Task t = tasks.get(taskIdx);
            System.out.println("\t This task has been whisked out of existence:");
            System.out.println("\t  " + t);
            tasks.remove(taskIdx);
            System.out.println("\t " + tasks.size() + " task(s) remaining.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Dook does not recognise this task!");
        }
        UI.br();
    }
}
