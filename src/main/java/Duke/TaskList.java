package Duke;
import java.util.ArrayList;
import java.util.List;

/** Contains the task list and manages various operations */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    /**
     * Constructor for TaskList.
     */
    public TaskList(){
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Specific task to be added.
     */
    public void add(Task task){
        tasks.add(task);
        ui.showAddedTask(task.toString());
    }

    /**
     * Deletes a task from the task list.
     *
     * @param i Index of task in the task list to be deleted.
     */
    public void delete(int i){
        ui.showDeletedTask(tasks.get(i).toString());
        tasks.remove(i);
    }

    /**
     * Returns list of tasks.
     *
     * @return List containing tasks.
     */
    public List<Task> getTasks(){
        return tasks;
    }

    /**
     * Marks task as done when it is completed.
     *
     * @param i Index of task in the task list to be marked as complete.
     */
    public void markAsDone(int i){
        tasks.get(i).markAsDone();
    }

    /**
     * Returns Size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize(){
        return tasks.size();
    }

}
