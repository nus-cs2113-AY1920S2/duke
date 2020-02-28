package Duke;
import java.util.ArrayList;
import java.util.List;

/** Manages task list operations */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    public TaskList(){
    }

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds to list of tasks and shows the added task.
     *
     * @param task task to be added.
     */
    public void add(Task task){
        tasks.add(task);
        ui.showAddedTask(task.toString());
    }

    /**
     * Adds to list of tasks without showing added task.
     *
     * @param task task to be added.
     */
    public void addToFindList(Task task){
        tasks.add(task);
    }

    /**
     * Deletes task from the list and shows the task deleted.
     *
     * @param i Index of the task in the task list.
     */
    public void delete(int i){
        ui.showDeletedTask(tasks.get(i).toString());
        tasks.remove(i);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void markAsDone(int i){
        tasks.get(i).markAsDone();
    }

    public int getSize(){
        return tasks.size();
    }

}
