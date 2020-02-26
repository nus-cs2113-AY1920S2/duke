package Duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    public TaskList(){
    }

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    public void add(Task task){
        tasks.add(task);
        ui.showAddedTask(task.toString());
    }

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
